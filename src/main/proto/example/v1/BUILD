load("@rules_java//java:defs.bzl", "java_proto_library")
load("@rules_proto//proto:defs.bzl", "proto_library")

java_proto_library(
    name = "examplev1_java_proto",
    visibility = ["//visibility:public"],
    deps = [":examplev1_proto"],
)

java_lite_proto_library(
    name = "examplev1_javalite_proto",
    visibility = ["//visibility:public"],
    deps = [":examplev1_proto"],
)

proto_library(
    name = "examplev1_proto",
    srcs = glob(["*.proto"]),
    strip_import_prefix = "/src/main/proto",
    deps = ["@com_google_protobuf//:timestamp_proto"],
)
