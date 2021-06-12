# coffee-store
simulation of a typical coffee store :)

The main idea behind this proh=ject is for self learnnig and implementing new techniques as an incremental update

phase 1 : 
CONTROLLER : Item(Maintains MenuItem and Inventory and Price)

API :Update Menu(accepts only one item)
METHOD :POST  
ACCEPTS : Name , Quantity, Price 
Cases Covered:
- Adds item to the list if not available in the Menu
- If Item Exists in Menu
    - Updates Item's Quantity by adding the Quantity to exiting quantity 
    - Updates Price based on the input

API - Delete Item from Menu
ACCEPTS : Name of the Item to be deleted
METHOD :DELETE
Cases Covered:
- Deletes the item from menu
-Returns valid message if the item is not available in the menu


CONTROLLER : Order(Service to Place Order)
API - Place Order request 
Accepts : Name and Quantity
METHOD :POST  
Cases Covered:
- Checks if the item is available in the Menu
    - If Yes
       - Checks for the Quantity and responds back with available quantiy
       - Returns  Total Amount
       - Returns  "Item not available" if Inventory Quantity is 0
    - else returns "Item not available in the List"


  






