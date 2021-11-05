workspace(name = "com_example_proto_java2")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

# ---

http_archive(
    name = "rules_jvm_external",
    sha256 = "995ea6b5f41e14e1a17088b727dcff342b2c6534104e73d6f06f1ae0422c2308",
    strip_prefix = "rules_jvm_external-4.1",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/4.1.tar.gz",
)

http_archive(
    name = "com_google_protobuf",
    sha256 = "53da54ce3e7ce8d6aebcb1e22ed2fabc6b36649f90e8c0944e3127761d2dbbeb",
    strip_prefix = "protobuf-98511c0e2f53e51bc2d593b4dee880030370c1db",
    urls = ["https://github.com/eikemeier/protobuf/archive/98511c0e2f53e51bc2d593b4dee880030370c1db.tar.gz"],
)

http_archive(
    name = "io_bazel_rules_docker",
    sha256 = "92779d3445e7bdc79b961030b996cb0c91820ade7ffa7edca69273f404b085d5",
    strip_prefix = "rules_docker-0.20.0",
    urls = ["https://github.com/bazelbuild/rules_docker/releases/download/v0.20.0/rules_docker-v0.20.0.tar.gz"],
)

# ---

load("@rules_jvm_external//:defs.bzl", "maven_install")

# ---

load("@com_google_protobuf//:protobuf_deps.bzl", "PROTOBUF_MAVEN_ARTIFACTS", "protobuf_deps")

protobuf_deps()

# ---

load("@io_bazel_rules_docker//repositories:repositories.bzl", container_repositories = "repositories")

container_repositories()

load("@io_bazel_rules_docker//java:image.bzl", java_repositories = "repositories")

java_repositories()

# ---

maven_install(
    artifacts = [
        "com.google.guava:guava:31.0.1-jre",
        "io.github.classgraph:classgraph:4.8.129",
        "io.github.toolfactory:narcissus:1.0.7",
    ] + PROTOBUF_MAVEN_ARTIFACTS,
    fetch_sources = True,
    maven_install_json = "//:maven_install.json",
    repositories = [
        "https://repo1.maven.org/maven2",
        "https://repo.maven.apache.org/maven2",
    ],
    strict_visibility = True,
)

# ---

load("@maven//:defs.bzl", "pinned_maven_install")

pinned_maven_install()
