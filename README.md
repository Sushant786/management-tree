## Management Tree

#### About

Write a method that takes a list of employees and displays employees in a management tree. An employee has an ID, name and manager ID.

Example:

| Id | Name | Manager Id |
:---: | :---: | :---:
| 1 | Tom | 0 |
| 2 | Mickey | 1 |
| 3 | Jerry | 1 |
| 4 | John | 2 |
| 5 | Sarah | 1 |

Expected output: 

->Tom

->->Jerry

->->Mickey

->->->John

->->Sarah 

For each level of management there is extra indentation to show who manages who.

● The tree must start from the root manager. The root manager will have a manager ID equal to 0. There is only 1 root manager.

● The root manager must have 1 set of indentation characters, ‘->’.  For example, ->Tom

● If an employee has a manager, then they must be displayed below their manager.

● For every level management, they must be another set of indentation characters, ‘->’.

##### How to run this project?

A dummyData.csv file has been placed in the 'src/resources' directory that contains the employees details. Feel free to add more employees details to see different outcomes.