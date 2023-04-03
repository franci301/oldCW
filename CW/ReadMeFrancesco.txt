Name: Francesco Gregotti
Student Number: 200926122


LEVEL ONE:
My code demonstrates inheritance in the following ways...
I have a superclass called Character.
This superclass is extended into at least two subclasses called Mage, Soldier, Tank and Boss

Subclass 1 (Mage):
Subclass Mage extends the superclass by adding at least one property and its getters and setters.
The name of the added property is "Ability" It was added as each character has an individual ability which is different to other characters.
This property is used in line 82 of the TestClasses file
Subclass Mage extends the superclass by overriding the following methods: activateAbility and addHealth (lines 22 and 64 in the superclass)
These overridden methods are used in the working code in the following places: Line 66 in the SimulateBattle class/file (activateAbility)
addHealth is called in line 13 in the Mage class/file

Subclass 2 (Boss):
Subclass Boss extends the superclass by adding at least one property and its getters and setters.
The name of the added property is "Probability" It was added as each character has an individual probability
This property is used in line 23 and 30 in SimulateBattle and line 87 in TestClasses.
Subclass Boss extends the superclass by overriding the following methods: activateAbility and setProbability(lines 22 and 55 in the superclass)
These overridden methods are used in the working code in the following places: Line 125 in SimulateBattle and Line 6 in Boss



LEVEL 2: 

Polymorphism consists of the use of the Substitution principle and Late Dynamic binding.

In my code, polymorphism is implemented in at least two places…

Example 1.
The substitution principle can be seen in use in the class TestClasses line 46. The name of the superclass used in this example is Character and the subclass used is Mage.
Late dynamic binding can be seen in TestClasses line 81 and SimulateBattle line 40.
This example of polymorphism is needed as the Mage class sets the health, damage, probability and armourClass of the Mage character to unique values which the other Character types do not use.
This allows my code to generate Boss's dependant on the characters health which will vary depending on the character type the user selects. 


