#!/usr/bin/env bash

cd ~/jianglibo-ws/plugin-with-quarkus/
git pull
./gradlew build "-Dquarkus.package.type=uber-jar" -x test
java -cp ./build/plugin-with-quarkus-0.1.3-SNAPSHOT-runner.jar picocli.AutoComplete -n p ai.datafocus.plugins.qst.EntryCommand
sudo mv p_completion /etc/bash_completion.d/

cd ~