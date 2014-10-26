Bars-and-Beers-Database-Project
===============================

Java Generator based on a revenue algorithm for my Bars and Beers Database project in MySQL

Class: Information and Data Management

Date: Fall 2013

I decided to put my Java generator for my database project on github. These are all the files I used to fill the database. My partner took care of the front end including the GUI. The project.sql file shows the syntax used to create the tables in our database. The generator is based on an algorithm inspired by the pictures in the formatting-files folder. Here is a list of the files and what they are:

<h3>Source Files</h3>

<h5>project.sql:</h5> 
creates the tables in the database

<h5>connection.java:</h5> 
creates randomTuple objects that generate bar data based on the algorithm. staffSQL method generates staff members for the bar and creates a skill rating.

<h5>randomTuple.java:</h5> 
generates bar data based on the algorithm

<h5>allbeer.java:</h5>
generates beer data based on scraped menus. Uses beer object to manage data that is added to database

<h5>allorigins.java:</h5>
generates repetative code to allbeer.java

<h5>clean.java and checkclean.java:</h5>
format scraped menu data into a workable format

<h5>fillHours.java and fillSells.java:</h5>
fill the table the class is named after

<h3>Formatting Files</h3>

<h5>.jpg files</h5>
pictures of the whiteboard I used when coming up with the bar revenue algorithm

<h5>allbeers.txt</h5>
beers used in filling the table

<h5>american-english.txt, barAdj.txt, barNoun.txt, capWord.txt, and word.txt</h5>
used in generating bar names

<h5>citiesbypop.txt</h5>
used in generating bar locations

<h5>firstnames.txt and lastnames.txt</h5>
used in generating names for the staff

<h5>menu*beers.txt</h5>
beer data scraped from menus of different <a href="http://worldofbeer.com/">World of Beer</a> locations

<h5>new*beers.txt</h5>
menu*beers.txt files in an easier-to-use format
