#!/bin/bash

set -eu

pushd greeter
cargo build --release
popd

cbindgen -l c -o bridges/greeter.h greeter

# 環境に合わせて書き換えてください
jextract=$HOME/opt/jdk-17-panama/bin/jextract

if ! command $jextract > /dev/null 2>&1
then
  echo 'jextract のパスを設定してください'
  exit 1
fi

$jextract \
-l greeter \
-d classes \
-t com.example \
./bridges/greeter.h


javac \
-encoding utf-8 \
-d ./classes \
-cp ./classes \
--add-modules jdk.incubator.foreign \
./src/Main.java

LD_LIBRARY_PATH=./greeter/target/release \
java \
-Dfile.encoding=utf-8 \
--enable-native-access=ALL-UNNAMED \
--add-modules jdk.incubator.foreign \
-cp ./classes Main
