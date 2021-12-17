#!/usr/bin/env bash

java -cp ./build/plugin-with-quarkus-0.1.3-SNAPSHOT-runner.jar picocli.AutoComplete -n p ai.datafocus.plugins.qst.EntryCommand
mv p_completion /etc/bash_completion.d/