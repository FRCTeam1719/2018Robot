#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "FRCTeam1719/2018Robot" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  echo -e "Building doc... \n"
  mkdir javadoc
  javadoc -d javadoc -sourcepath ./ -subpackages org.usfirst.frc.team1719.robot
  cp -R javadoc $HOME/javadoc-latest
  openssl aes-256-cbc -K $encrypted_cededcd599c8_key -iv $encrypted_cededcd599c8_iv -in travis_id_rsa.enc -out travis_id_rsa -d 
  chmod 600 travis_id_rsa
  eval `ssh-agent -s`
  ssh-add travis_id_rsa
  REPO=`git config remote.origin.url`
  SSH_REPO=${REPO/https:\/\/github.com\//git@github.com:}
  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages $REPO gh-pages > /dev/null
  cd gh-pages
  git rm -rf ./javadoc
  cp -r $HOME/javadoc-latest/* ./
  git add -f .
  git commit -m "Latest javadoc on build $TRAVIS_BUILD_NUMBER"
  git push -fq $SSH_REPO gh-pages > /dev/null
  echo -e "Published to gh-pages \n"

fi
