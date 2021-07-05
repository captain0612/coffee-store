# coffee-store

simulation of a typical coffee store :)

The main idea behind this proh=ject is for self learnnig and implementing new techniques as an incremental update

phase 1 :

API :Update Menu(accepts only one item)
METHOD :POST  
ACCEPTS : Name , Quantity, Price Cases Covered:

- Adds item to the list if not available in the Menu
- If Item Exists in Menu
    - Updates Item's Quantity by adding the Quantity to exiting quantity
    - Updates Price based on the input

API - Delete Item from Menu ACCEPTS : Name of the Item to be deleted METHOD :DELETE Cases Covered:

- Deletes the item from menu -Returns valid message if the item is not available in the menu

CONTROLLER : Order(Service to Place Order)
API - Place Order request Accepts : Name and Quantity METHOD :POST  
Cases Covered:

- Checks if the item is available in the Menu
    - If Yes
        - Checks for the Quantity and responds back with available quantiy
        - Returns Total Amount
        - Returns  "Item not available" if Inventory Quantity is 0
    - else returns "Item not available in the List"

Phase - 2 Updated API's to accept input as a list

Phase - 3

TABLE UPDATES - 2 New tables Order - To store Order ID(primary Key)
Cart - list of items for Order ID (foriegn Key Maps to Order_id in order Table)

API - createCart METHOD : POST  
Accepts item and Quantity and responds back with availableitems , total Amount and Order_ID and stores the cart items in
Cart table

API - Confirm Order METHOD : POST Accept : Order_id API pulls all the items from Cart and then it updates the inventory
and completes the Order.



  






