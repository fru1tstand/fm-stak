# Stak Developer Guide
A step by step guide on how to set up your workspace.

## Workspace
Stak uses gradle to handle dependencies so any IDE is fine for development, but I primarily use
[IntelliJ](https://www.jetbrains.com/idea/) so there may be IntelliJ-specific examples in here.

### Version Control
This project uses Git.

There are two sides to pieces to this codebase:
  + The client side (this repo)
  + The server side (the submodule located at `libs/fm-stak-server`)

##### Cheat Sheet
  + `git commit --amend [--no-edit]` Modify the last commit. Only use this when your commit is local
    and hasn't been pushed. This is useful for fixing a commit you've created that's failed pre-push
    unit tests.  
    `--no-edit` avoids changing commit description if desired.
  + `git submodule foreach git pull origin master` Fetches updates for all submodules.

#### Initializing the Repository
  1. Create a folder where you want source to live and navigate to that folder.  
     ```
     $ mkdir ~/workspaces/stak
     $ cd ~/workspaces/stak
     ```
  2. Clone the repo  
     ```
     $ git clone git@github.com:fru1tstand/fm-stak.git .
     ```
  3. Initialize submodules
     ```
     $ git submodule init
     $ git submodule update
     ```
  4. Copy `pre-push` into both `.git/hooks` folders (the one located in this repo and in the
     submodule folder). This pre-push hook will simply run all tests before pushing to ensure
     commits do not break master.

#### Working with Submodules

When making updates to both the client and server, be sure the server code is committed first. The
server-dependent changes (or a prior commit) should contain the submodule version update. This
ensures breakages don't occur due to non-existent server-side changes.

For example, I'm creating the "delete user" feature:
  1. I've added both client and server-side code for this.
  2. In the server submodule:
     ```
     $ git add .
     $ git commit
     $ git push
     ```
  3. In the client repo:
     ```
     $ git status
       (there will be an entry that says `libs/fm-stak-server (new commits)`
     $ git add .
       (this will stage `libs/fm-stak-server`)
     $ git commit
     $ git push
     ```
  4. Push the client repo changes `git push`

### Workflow
Stak uses gradle to handle dependencies, thus, we employ gradle tasks to build, run, and test the
application.

  + Build - Compiles the project and reports errors  
    `./gradlew clean build`
  + Run - Starts client and server  
    [to be added]
  + Test - Runs all unit and integration tests  
    `./gradlew clean test`

IntelliJ has built-in assistance tools for building, running, testing, and coverage. Please use them
and ensure you're building unit tests. Coverage does not need to be 100%, but ensure you're making
your best effort in covering critical paths.

### Stak
  + Kotlin - Language of choice
  + Guava - Google's core Java library
  + ControlsFX - JavaFX enhancements (think Bootstrap for JavaFX)
  + JUnit Jupiter (JUnit 5) - Unit testing framework
  + TestFX - Unit testing for JavaFX
