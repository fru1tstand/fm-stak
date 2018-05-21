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
    `./gradlew clean run`
  + Test - Runs all unit and integration tests  
    `./gradlew clean test`

IntelliJ has built-in assistance tools for building, running, testing, and coverage. Please use them
and ensure you're building unit tests. Coverage does not need to be 100%, but ensure you're making
your best effort in covering critical paths.

#### IntelliJ Users
Due to use of Dagger, one needs to enable annotation processing within IntelliJ: 
`File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors`. Simply check
the `Enable annotation processing` box and leave all other values at default.

To improve workflow, it's advisable to add the `kaptKotlin` gradle task to the default kotlin
configuration file within the `Run | Edit Configurations...` panel.

One can also add file watchers to the `StakComponent.kt`, `StakModule.kt`, and `ComponentsModule.kt`
files to trigger the `kaptKotlin` gradle task on file change.

### Stak Libraries
  + Kotlin - Language of choice
  + Guava - Google's core Java library
  + ControlsFX - JavaFX enhancements (think Bootstrap for JavaFX)
  + JUnit Jupiter (JUnit 5) - Unit testing framework
  + TestFX - Unit testing for JavaFX
  + Dagger2 - Dependency injection

### Programming
#### Project layout
Most, if not all, of the code that's written will belong in the `components` package. With minor
exceptions, a class will almost always have an interface to it that's an exact mirror of all the
public methods within the class. The interface will be called `foo`, where the class itself will be
called `fooImpl` and belong in the `impl` package just below the interface. We do this to both
separate implementation from interface, but also allow easier unit testing of other components. As
components will have other component dependencies, we don't want to instantiate all the other
dependencies, so we simply mock the interface and happily test the impl with those mocks.

When adding new components, don't forget to add an `@binds` definition within `ComponentsModule`
of the respective project. For the desktop client, that's `me.fru1t.stak.ComponentsModule` and for
the server, that's `me.fru1t.stak.server.ComponentsModule`.

#### Unit testing
There are several types of tests one may need to create.
  + Normal tests  
    These tests have no dependency on server or ui elements and purely test code paths. These tests
    can run on the vanilla JUnit 5 platform with no modifications. Tests should be annotated with
    `@Test`. See `me.fru1t.stak.server.components.example.impl.ExampleImplTest` and associated
    files. You can almost always guarantee each test here will be run synchronously, meaning, it's
    fine to declare a `@BeforeEach` clause in these tests.  
    + "How do I know my test is a normal test?"  
      Trust me. You'll know. You won't be fighting anything related to UI or server routing.
  + Server tests  
    These tests have dependency on server code (for example, routing). These tests require the ktor
    testing framework on top of JUnit 5. These tests are annotated the same as normal test `@Test`,
    but extend the `io.ktor.server.testing.withTestApplication` method and contain a test server
    routing definition via `application.routing`. See
    `me.fru1t.stak.server.routing.IndexHandlerTest` and associated files for an example. Much like
    the normal tests, these tests are almost always run synchronously.  
    + "How do I know my test is a server test?"
      Did you add routing? Did you touch a file that has routing? If yes, you'll be making a server
      test.
  + JavaFX tests  
    These are the most annoying tests as they've got many conditions. First, they can be run
    asynchronously for performance reasons (meaning you cannot have a `@BeforeEach` setup method,
    and instead must rely on calling a helper method within each test). Second, each test is
    annotated with `@FxTest` with the test class itself extending
    `me.fru1t.fx.testing.FxApplicationTest`. This is required as JavaFX must be handled via the main
    UI thread at all times. The TestFX framework handles this work for us.  
    + "How do I know my test is a JavaFX test?"
      If you need to test anything related to UI (using Scenes, Stages, etc) you're making a JavaFX
      test.
