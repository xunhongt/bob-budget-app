import React from 'react'
import { Button, Form, Dropdown } from 'semantic-ui-react'

function CategoryAddForm({ categoryName, categoryType, handleInputChange, handleAddCategory }) {

  const categoryTypeOptions = [
    { key: 'expenditure', value: 'E', text: 'Expenditure' },
    { key: 'income', value: 'I', text: 'Income' }
  ]
  
  const createBtnDisabled = categoryName === '' || categoryType === ''
  return (
    <>
      <h1>Add Category</h1>
      <Form onSubmit={handleAddCategory}>
        <Form.Group>
          <Form.Input
            name='categoryName'
            width={4}
            placeholder='Category Name'
            onChange={handleInputChange}
            value={categoryName}
          />
          <Dropdown
            name='categoryType'
            placeholder='Select Category Type'
            fluid
            selection
            width={2}
            onChange={handleInputChange}
            options={categoryTypeOptions}
            value={categoryType}
          />
          <Button 
            icon disabled={createBtnDisabled}
            width={4}>
            Add
          </Button>
        </Form.Group>
      </Form>
    </>
  )
}

export default CategoryAddForm