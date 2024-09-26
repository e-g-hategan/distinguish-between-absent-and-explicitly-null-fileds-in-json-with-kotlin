# Distinguish Between Absent and Explicitly Null  Fileds in Json with Kotlin

Such a distinction is crucial in the context of AWS IoT at least where this is the only way to remove a field from the shadow of an AWS IoT Thing. 

More specifically absent fields are ignored while explicitly null fields will remove the respective field from the shadow. 

This simple and elegant solution allows one to implement this distinction with minimal coding. 
