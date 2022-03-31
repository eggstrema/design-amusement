# Design Amusement

## Motivation for this repository
The excellent book [Agile Technical Practices Distilled](https://leanpub.com/agiletechnicalpracticesdistilled) lists and
categorizes a comprehensive list of code smells (among a number of other important lessons and exercises). This
repository is meant to be a playground where people can find an example of each smell. It provides an opportunity to
get hands-on experience how a single code smell makes maintenance harder than necessary. It is recommended to implement
each feature request first in the original design and a second time after refactoring the design to accomodate the
upcoming changes. 

## Setting the Stage
You are lucky to snatch a gig, where you write tha middle layer of the backend software for the greatest amusement park
in the country. Since the hiring process focussed on finding the best candidate available on the market, your position
has been empty for a little while and the list of desired new features has grown steadily. Management has high hopes
that you'll be able to overcome the troubles that your predecessors had. Honestly, they were probably not the brightest
people on the market, who had strong opinions about arbitrary design practices. 

## Constraints
Since you inherited the middle layer of the application, you won't have to deal with any external systems, but can
solely focus on changing Java code. Since the company has not found new developers for the other layers, you cannot
change the interfaces to any other systems. You may change the tests and all of the production code as you see fit.

