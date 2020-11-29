# Java-8 Feature

What is a Stream?
- Stream is an abstraction
- The abstraction focuses on all rather than the little parts
- Stream helps us to move from an imperative way to functional programming [imperative = unavoidable]
- Used with collection

_[Note : Streams are lazily constructed i.e intermediate operations are not evaluated until terminal operation is invoked]_

## What is difference with Collections and Stream?
- Collections are used to store and group the data in a particular data structure like List, Set or Map. But, streams are used to perform complex data processing operations like filtering, matching, mapping etc on stored data such as arrays, collections
- Collections are mainly about data and streams are mainly about operations on data.

## Data Modification
- In Collection we can add, remove and modify data. Stream consumes a source, performs operations on it and returns a result. Stream doesn't modify the source.

## Difference Between Intermediate and Terminal Operations?
- Stream operations are combined into pipelines to process streams. All operations are either intermediate or terminal.
  Intermediate operations are those operations that return Stream itself allowing for further operations on a stream.
  These operations are always lazy, i.e. they do not process the stream at the call site, an intermediate operation can only process data when there is a terminal     operation. Some of the intermediate operations are filter, map and flatMap.
  Terminal operations terminate the pipeline and initiate stream processing. The stream is passed through all intermediate operations during terminal operation       call. Terminal operations include forEach, reduce, Collect and sum.

