package com.example;

import com.google.common.collect.ImmutableMultimap;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.Resource;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ResourceList.ResourceFilter;
import io.github.classgraph.ScanResult;
import java.io.File;
import java.util.Map.Entry;
import java.util.logging.Logger;

public final class ClassCheck {
  private static final Logger LOG = Logger.getLogger(ClassCheck.class.getSimpleName());

  private static final ResourceFilter MY_FILTER =
      resource -> {
        String path = resource.getPath();
        if (path.equals("module-info.class")) {
          return false;
        }
        if (!path.endsWith(".class") || path.length() < 7) {
          return false;
        }
        // Check filename is not simply ".class"
        char c = path.charAt(path.length() - 7);
        return c != '/' && c != '.';
      };

  private ClassCheck() {}

  public static void main(String... args) {
    ClassGraph.CIRCUMVENT_ENCAPSULATION = ClassGraph.CircumventEncapsulationMethod.NARCISSUS;
    ClassGraph classGraph = new ClassGraph();
    try (ScanResult scan = classGraph.scan();
        ResourceList resourceList = scan.getAllResources().filter(MY_FILTER)) {
      ImmutableMultimap.Builder<File, String> builder = ImmutableMultimap.builder();
      for (Entry<String, ResourceList> duplicate : resourceList.findDuplicatePaths()) {
        try (ResourceList duplicates = duplicate.getValue()) {
          for (Resource resource : duplicates) {
            builder.put(resource.getClasspathElementFile(), duplicate.getKey());
          }
        }
      }
      ImmutableMultimap<File, String> map = builder.build();

      for (File file : map.keySet()) {
        LOG.warning(() -> String.format("Duplicate resource in %s:", file));
        for (String resource : map.get(file)) {
          LOG.warning(() -> String.format(" -> %s", resource));
        }
      }
    }
    LOG.info("Check finished");
  }
}
