public DiceSet( int count, int sides ) {
      ds = new Die[ count ];
      for (int i = 0; i <= ds.length - 1; i++) {
        ds[i] = new Die (sides);
      }
   }


k >= 1 and n>= four
roll method no number return and roll individual return void 
get value from that

to String methods

is identitcal 
	-ds1 		ds2
	5			6
	4			3
	3			1
	2			2
	1			5


	two dice sides, are the values the same
	5 and 5 at bottom ;; must mark as visited somehow in which array/list

	2) also is identical easy way is 6 sided die 
	matching numbers : extra challenge

	1) sides and size? Easy way

main method in dice set, allows to test methods
	-dice set makes calls to the die class
	-possible overlap there
	-tests in dice set class could cover both methods
		-document in comments "this will be tested in dice set class in this method...."

If you write a die inst in your test that is 4 sides die roll 3 times
	-you get a 4 a 4 and a 2, but because a random generated numbers cant guarantee I'll see all 4 if all tests
	-won't count us off but re-run test, to see all the numbers show up

	Highroll.java
	roll as many times 
	display as a menu of selections and prompt underneath last selection to enter something;

		-can makes them type in "calc...prompt them to enter to enter and perform the activity 
		MUST prompt
		once program performs then must display activity again
		while true, while 1 while forever until you get a Q
			while input not Q

	roll a single 

	>>2 
	which? >> and enter 5 to that then perform the rule


	roll over and over, calculate the score until you get a Q

	calculation should display score or don't need to calculate and just see what got then decide to roll again
-----------

	High Roll class
		have an instance of a DiceSet
		does not depend on all the DiceSet

		composition empty diamond v. aggregation full diamond 
		implemeneting dice set and DS array manages die instance



get Sides include but not useful

Class v. public - educational tool just to implement an instance?...

Void so take out return line

d. aidfj



Classwide: static, CalendarstuffTester CalendarStuff.blah repeated
	because all defined as 
	Die. blah 

	How we refer to die we are running it off of
	not run off specific instance, run off clas as a whole

	D.toString ();  CHECK LINE 112

Class method v. instance method, must instantiate method 
Die.d = new die  d. blah

teaching tool to let us see the difference, probably won't use the method
	-important but not 


	TOOK out int and return line
	replace with void- cant give a single value for all the dies rolled in the array

	this was in line 83
	return ds[dieIndex].roll();


	public int rollIndividual( int dieIndex ) {
      if (dieIndex > ds.length -1) {
      throw new IllegalArgumentException(); 
    }
    return ds[dieIndex].roll();
   }



   BUT call either toString method or call Sum method but not display
   -display later














