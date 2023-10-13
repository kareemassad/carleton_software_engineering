# 1.
- Element
- Tag
- Text
- Root
- Attribute 

# UML (Using PlanUML)

Render Here: https://www.plantuml.com/plantuml/uml/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000

```PlantUML
@startuml
class XMLElement {
    - tag : String
    - text : String
    - subElements : List<XMLElement>
    - attributes : Map<String, String>
    + XMLElement(tag: String)
    + addSubElement(element: XMLElement): void
    + setText(text: String): void
    + addAttribute(key: String, value: String): void
    + getText(): String
    + getTag(): String
    + getSubElements(): List<XMLElement>
    + getAttribute(key: String): String
    + toString(): String
}
@enduml
```
