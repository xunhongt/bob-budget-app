import React from 'react'
import { Dropdown, Button, Form } from 'semantic-ui-react'

function BudgetAddForm({ categories, categoryName, amount, description, handleInputChange, handleAddBudget }) {
  const categoryNameOptions = categories.map(category => {
    return (
      { key: category.categoryName, value: category.categoryName, text: category.categoryName }
    )
  })

  const createBtnDisabled = categoryName === '' || amount === '' || description === ''

  return (
    <>
      <h1>Add Budget</h1>
      <Form onSubmit={handleAddBudget}>
        <Form.Group>
          <Form.Input
              name='amount'
              width={4}
              placeholder='Input Amount'
              onChange={handleInputChange}
              value={amount}
          />
          <Dropdown
            label='Category Name'
            name='categoryName'
            placeholder='Select Category Name'
            selection
            width={2}
            onChange={handleInputChange}
            options={categoryNameOptions}
            value={categoryName}
          />
          <Form.Input
            name='description'
            width={7}
            placeholder='Input Description'
            onChange={handleInputChange}
            value={description}
          />
          <Button 
            icon disabled={createBtnDisabled}
            width={4}>
            Create
          </Button>
        </Form.Group>
      </Form>
    </>
  )
}

export default BudgetAddForm