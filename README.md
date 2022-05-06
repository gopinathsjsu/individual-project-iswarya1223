# Product Management Application

## Problem Statement
Develop an inventory management system in which inventory details and cards details will be provided in the CSV file. Another CSV file will be provided, containing the orders information. There will be a maximum limit on the type of items (category) which can be ordered and will be set in the program at the start. In case of successful order checkout, total amount to be paid will be displayed, while in case of error, an error output file needs to be generated.

## Instructions:
- clone the repository in your local system.
- in /src folder issue "javac Billing.java".
- java Billing
- Before executing the application change the file path of the stock data, card data , error.log and output file path to your local repo file system.

## Design Patterns Used

### Factory
 - It is a creational design pattern that provides an interface for creating objects in a superclass, but allows subcasses to alter the type of objects that will be created.
 - Used this design pattern in reading the database files like stock,card data , user input data . All these 3 classes have common feature set so developed an interface out of these functionalities and used factory pattern so that I can create objects in super class.
 ![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/factory%20pattern.png)
 
 ### Singleton:
- It is creational design pattern that lets you ensure that a class has only one instance, while providing the global access point to this instance.
-  Used Singleton pattern in checking the CapLimit of the product categories.
![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/singleton%20pattern.png)

### Test Case 1:

#### Input:

![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/test1.png)

#### Output:

![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/test1results.png)

### Test Case 2:

#### Input:

![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/test2.png)

#### Output:

![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/test2results.png)


### Test Case 3:

#### Input:

Input file with new card for payment
![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/test3.png)

Card details before running the program:
![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/testcase3_card_details_before_run.png)

#### Output:

![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/test3results1.png)

updated card database after running the program
![](https://github.com/gopinathsjsu/individual-project-iswarya1223/blob/main/.idea/test3results2.png)


