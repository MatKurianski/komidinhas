Feature: Create a product

Scenario: Creating a product with valid information
  Given url 'http://localhost:8080/product'
  And request { name: 'Cone Trufado', description: 'Cone delicioso', price: '6.00', image_url: 'idk.png' }
  When method post
  Then status 201
  And match response == { id: '#notnull', name: 'Cone Trufado', description: 'Cone delicioso', price: 6.00, image_url: 'idk.png' }