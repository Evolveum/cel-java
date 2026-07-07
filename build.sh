#!/bin/bash

bazel build ... --java_language_version=8 --java_runtime_version=8 --build_tag_filters=-conformance_maven
