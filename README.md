### Element Identification

```java

//  /html/body/main/div/form/div/div[1]/label[1]/input -> Absolute xpath
// //*[@id="my-text-id"] -> Relative xpath

// ------------------- Golden Rule
//tagName[@attribute='value']

// ------------------- Variations
//*[@attribute='value']
//tagName[@attribute='value'][@attribute='value']

(//tagName[@attribute='value'])[index]

//tagName[text()='value']
//tagName[contains(text(),'valuePart')]

//tagName[contains(@attribute,'valuePart')]
//tagName[starts-with(@attribute,'valuePart')]
//tagName[ends-with(@attribute,'valuePart')]

// ------------------- Xpath AXIS

//tagName[@attribute='value']/AXIS::tagName

//tagName[@attribute='value']/parent::tagName
//tagName[@attribute='value']/ancestor::tagName

```

### Best Practices for Element Identification
- The locator must be unique
- Prefer CSS Selector over Xpath
- Locator must always match the business requirements (Especially for the index)
