#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "FRCTeam1719/2017Robot" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  echo -e "Building doc... \n"
  mkdir javadoc
  javadoc -d javadoc -sourcepath ./ -subpackages org.usfirst.frc.team1719.robot
  cp -R javadoc $HOME/javadoc-latest
  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/frcteam1719/2017Robot gh-pages > /dev/null
  cd gh-pages
  git rm -rf ./javadoc
  cp -r $HOME/javadoc-latest/* ./
  git add -f .
  git commit -m "Latest javadoc on build $TRAVIS_BUILD_NUMBER"
  git push -fq origin gh-pages > /dev/null

  echo -e "Published to gh-pages \n"

fi
