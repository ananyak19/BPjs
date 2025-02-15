# BPjs: A JavaScript-based Behavioral Programming Runtime.

This repository contains a javascript-based [BP](http://www.b-prog.org) library.

[![Build Status](https://travis-ci.org/bThink-BGU/BPjs.svg?branch=master)](https://travis-ci.org/bThink-BGU/BPjs)
[![Coverage Status](https://coveralls.io/repos/github/bThink-BGU/BPjs/badge.svg?branch=master)](https://coveralls.io/github/bThink-BGU/BPjs?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.bthink-bgu/BPjs/badge.png?style-plastic)](https://repo.maven.apache.org/maven2/com/github/bthink-bgu/BPjs/)
[![Documentation Status](http://readthedocs.org/projects/bpjs/badge/?version=master)](http://bpjs.readthedocs.io/en/master/)
[![JavaDocs](https://img.shields.io/badge/javadocs-browse-green.svg)](http://www.javadoc.io/doc/com.github.bthink-bgu/BPjs/)

#### License
* BPjs is open sourced under the [MIT license](http://www.opensource.org/licenses/mit-license.php). If you use it in a system, please provide
a link to this page somewhere in the documentation/system about section.
* BPjs uses the Mozilla Rhino JavaScript engine. Project page and source code can be found [here](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/Rhino).

#### Academic Citation

If you use BPjs in an academic work, please consider citing it as:

> Bar-Sinai M., Weiss G. (2021) Verification of Liveness and Safety Properties of Behavioral Programs Using BPjs. In: Margaria T., Steffen B. (eds) Leveraging Applications of Formal Methods, Verification and Validation: Tools and Trends. ISoLA 2020. Lecture Notes in Computer Science, vol 12479. Springer, Cham. https://doi.org/10.1007/978-3-030-83723-5_14

[bibtex](docs/source/Examples_code/bpjs.bib)

---

## Getting BPjs
* For Maven projects: Add BPjs as dependency. Note that the version number changes.

````xml
<dependencies>
    ...
    <dependency>
        <groupId>com.github.bthink-bgu</groupId>
        <artifactId>BPjs</artifactId>
        <version>0.12.2</version>
    </dependency>
    ...
</dependencies>
````

* Clone, fork, or download the [starting project](https://github.com/bThink-BGU/SampleBPjsProject).
* Download the `.jar` files directly from [Maven Central](https://repo.maven.apache.org/maven2/com/github/bthink-bgu/BPjs/).
* The project's [Google group](https://groups.google.com/forum/#!forum/bpjs)

## Documentation

* [Devoxx Belgium 2018 talk](https://www.youtube.com/watch?v=PW8VdWA0UcA) introducing Behavioral Programming and BPjs.
* Presentations: [Introduction](https://www.slideshare.net/MichaelBarSinai/introducing-bpjs-web)
                 [Deeper dive](https://www.slideshare.net/MichaelBarSinai/deep-dive-into-bpjs)
* [Tutorial and Reference](http://bpjs.readthedocs.io/en/develop/)
* [API Javadocs](http://www.javadoc.io/doc/com.github.bthink-bgu/BPjs/)

## Change Log for the BPjs Library.

### 2022-07
* :arrow_up: :sparkles: Moved to Rhino 1.7.14. This means many new features, including template strings and better Java interoperability. See full list [on Mozilla's site](https://github.com/mozilla/rhino/releases/tag/Rhino1_7_14_Release). ([#186](https://github.com/bThink-BGU/BPjs/issues/186)).
    * :part_alternation_mark: Refactored internals fo better fit the new Rhino version. We now have a proper context factory, as well as try-with-resources on all context invocations.
* :part_alternation_mark: `BProgramRunnerListener` defautls to ignore errors during BProgram execution, instead of printing the details to `stderr`. This behavior remains in `BProgramRunnerListenerAdapter`, where it makes more sense. Of course, client code can override these methods. ([#191](https://github.com/bThink-BGU/BPjs/issues/191)).
* :arrow_up: Printing problematic b-thread's name during serialization errors ([#169](https://github.com/bThink-BGU/BPjs/issues/169)).
* :sparkles: Custom serialization for the common and non-serializable `java.util.Optional` and JavaScript's `Set()`. ([#189](https://github.com/bThink-BGU/BPjs/issues/189))
    * This is a basis for custom serializations in general, we now have an initial mechanism in place.
* :arrow_up: Improved wrapping of Java objects as they come to JavaScript: Java strings are now treated as native JS strings, so using the `===` operator works as expected. ([#104](https://github.com/bThink-BGU/BPjs/issues/104)).
* :bug: Verification stops on ECMAScript errors, instead of hanging ([#49](https://github.com/bThink-BGU/BPjs/issues/49)).
* :bug: Improved error reporting when trying to sync outside of a b-thread ([#174](https://github.com/bThink-BGU/BPjs/issues/174)).
* :arrow_up: Informative error message when requesting a list of events and one of the events is `null` ([#184](https://github.com/bThink-BGU/BPjs/issues/184)).
* :arrow_up: Logging is turned off by default during verification. Call `BPjs.setLogDuringVerification(true)` to enable them again ([#160](https://github.com/bThink-BGU/BPjs/issues/160)).
* :sparkles: Added local [JACOCO](https://www.jacoco.org) code coverage reports.

### 2022-02 (Including the 1st BP Day Hackathon)
* :part_alternation_mark: Calling `bp.sync` from global scope during runtime does not cause an ugly exception anymore ([#174](https://github.com/bThink-BGU/BPjs/issues/174)). The error is reported to the listeners.
    * Also when making the illegal call from an interrupt handler.
* :arrow_up: Improved JavaScript code error detection during analysis
* :arrow_up: Improvements to handling `null`s in event set arrays ([#178](https://github.com/bThink-BGU/BPjs/issues/178)).
* :arrow_up: Improvements to handling Rhino context in BPjs class ([#176](https://github.com/bThink-BGU/BPjs/issues/176)).
* :arrow_up: Improvements to the `MapProxy` classes, allowing client code pre-process changes.
* :arrow_up: `JsEventSet` now honors the event set name during equality checks.
* :part_alternation_mark: `BEvent::getDataField` data accessors wrap the lower-level `getData()` ([#161](https://github.com/bThink-BGU/BPjs/issues/161)).
* :part_alternation_mark: API improvments on `DfcBProgramVerifier` ([#173](https://github.com/bThink-BGU/BPjs/issues/173)).
* :put_litter_in_its_place: Consolidated tests for `BProgramJsProxy`.

### 2022-01
* :arrow_up: Improvements to the `BEvent` class, to make it better support client Java code.

### 2021-10
* :sparkles: Added [contribution guidelines](CONTRIBUTING.md) ([#139](https://github.com/bThink-BGU/BPjs/pull/139)).
* :sparkles: Reference to the (finally) published paper describing the updated BPjs model semantics, w.r.t liveness ([#99](https://github.com/bThink-BGU/BPjs/pull/99)).

### 2021-05

* :sparkles: Client code can change the execution services BPjs uses ([#165](https://github.com/bThink-BGU/BPjs/pull/165)).
* :sparkles: `bp.log.XXX` can be redirected to print streams other than `System.out`. ([#159](https://github.com/bThink-BGU/BPjs/pull/159)).
* :arrow_up: Documentation updates.
* :arrow_up: Improved performance of BTSS equality ([#164](https://github.com/bThink-BGU/BPjs/issues/164)).
* :arrow_up: Removed broken JS code in tests ([#153](https://github.com/bThink-BGU/BPjs/issues/153)).


### 2021-02

* :sparkles: :sparkles: :tada: :sparkles: :tada: :sparkles: :rainbow: :sparkles: :sparkles: :sparkles: BPjs now uses Rhino's native continuation equality and hash code ([#116](https://github.com/bThink-BGU/BPjs/issues/116). Also, :tada: :rainbow: :sparkles:.
* :arrow_up: Scope and context creations across BPjs have been consolidated to utility methods in a new class called `BPjs`.
* :arrow_up: Event selection strategies that ignore the synchronization statement data field, issue a warning when b-threads put data there ([#151](https://github.com/bThink-BGU/BPjs/issues/151)).
* :arrow_up: Default priorities in `PrioritizedBSyncEventSelectionStrategy` and `PrioritizedBThreadsEventSelectionStrategy` can be changed ([#115](https://github.com/bThink-BGU/BPjs/issues/115) and [PR#147](https://github.com/bThink-BGU/BPjs/pull/147)).
* :arrow_up: Updated docs.
* :arrow_up: `BProgram` uses an override-able protected method to access its global scope, in case you want to intercept these calls.
* :bug: Fixed an issue that caused `toString` for JavaScript objects to crash at certain cases ([#145](https://github.com/bThink-BGU/BPjs/issues/145)).
* :bug: Storage consolidation conflicts have proper `equals` and `hashCode`.

### 2021-01

* :sparkles: `BProgramSyncSnapshot`s are serialized using a single stream, and with the b-program's original scope as a top-level scope (([#126](https://github.com/bThink-BGU/BPjs/issues/126)).
* :sparkles: BThread snapshots do not retain their entry point function after their first sync point. This results in lower memory footprint, and a more efficient de/serialization. These result in improved analysis performance and efficiency.
* :sparkles: Event sets (and, by inheritance, events) are now composable. So you can write, e.g., `bp.Event("A").or(bp.Event("B")).negate()` to create an event set that contains all events except `A` and `B`.
* :sparkles: Another easy event set composition/creation added: `bp.eventSets` gives access to `EventSets`, with methods such as `bp.eventSets.anyOf(...)` and `bp.eventSets.not(...)`
* :arrow_up: Logging now allows formatting, using Java's MessageFormat.
* :arrow_up: Expressive `toString` on JavaScript sets ([#135](https://github.com/bThink-BGU/BPjs/issues/135)).
* :arrow_up: Error messages when passing non-events to a synchronization statement are more informative ([#131](https://github.com/bThink-BGU/BPjs/issues/131)).
* :arrow_up: Improved logging consistency ([#132](https://github.com/bThink-BGU/BPjs/issues/132)).
* :arrow_up: Error messages when JS event sets do not return a `Boolean` are more informative ([#138](https://github.com/bThink-BGU/BPjs/issues/138)).
* :arrow_up: BThread data documentation updated ([#134](https://github.com/bThink-BGU/BPjs/issues/134)).
* :arrow_up: BProgram storage can be updated by the b-program before b-threads run ([#129](https://github.com/bThink-BGU/BPjs/issues/129)).
* :arrow_up: Changes made to the b-program store after the last sync of a b-thread are now applied ([#130](https://github.com/bThink-BGU/BPjs/issues/130)).
* :arrow_up: More tests (logger, JSProxy, ScriptableUtils, OrderedSet, JsEventSet, some event selection strategies).
* :bug: :tada: JS-semantics are applied for `equals` and `hashCode` for b-thread and b-program data ([#144](https://github.com/bThink-BGU/BPjs/issues/144)).
* :bug: Fixed the note color issue in the documentation ([#137](https://github.com/bThink-BGU/BPjs/issues/137)).
* :bug: Fixed the bench marker.
* :bug: Fixes a crash when a JS event set predicate returns `null` instead of a boolean.
* :put_litter_in_its_place: Significant cleanup of the b-program I/O area.
* :put_litter_in_its_place: Significant cleanup of the event set area. Some methods moved from `ComposableEventSet` to `EventSets`.
* :put_litter_in_its_place: Some documentation updates and corrections.
* :put_litter_in_its_place: Removed extraneous dependencies from `pom.xml` ([#133](https://github.com/bThink-BGU/BPjs/issues/133)).

[Earlier Changes](changelog-2020.md)

Legend:
* :arrows_counterclockwise: Change
* :sparkles: New feature
* :tada: New feature, but more exciting
* :part_alternation_mark: Refactor (turns out this sign is called "part alternation mark" and not "weird 'M'", so it fits).
* :put_litter_in_its_place: Deprecation
* :arrow_up: Upgrade
* :bug: Bug fix
