import React from 'react'
import { Dropdown, Form, Button, Input, Table } from 'semantic-ui-react'

function BudgetsTable({ 
              categorySearch, 
              budgets,
              budgetId,
              categories,
              categoryName, 
              amount, 
              description, 
              handleInputChange, 
              handleSearchCategory,
              handleDeleteBudget,
              handleUpdateBudget,
              setUpdateBudgetForm }) {

  let budgetList

  const categoryNameOptions = categories.map(category => {
    return (
      { key: category.categoryName, value: category.categoryName, text: category.categoryName }
    )
  })

  const createBtnDisabled = categoryName === '' || amount === '' || description === ''

  if (budgets.length === 0) {
    budgetList = (
      <Table.Row key='no-budget'>
        <Table.Cell collapsing textAlign='center' colSpan='6'>No Budgets</Table.Cell>
      </Table.Row>
    )
  } else {
    budgetList = budgets.map(budget => {
      return (
		<Table.Row key={budget.budgetId}>
		  <Table.Cell>{budget.budgetId}</Table.Cell>
		  <Table.Cell>{budget.categoryName}</Table.Cell>
		  <Table.Cell>{budget.amount}</Table.Cell>
		  <Table.Cell>{budget.description}</Table.Cell>
		  <Table.Cell>{budget.dateCreated}</Table.Cell>
		  <Table.Cell>{budget.dateUpdated}</Table.Cell>
      <Table.Cell> 
          <Button
              onClick={() => setUpdateBudgetForm(budget)}>
            Update
          </Button>
      </Table.Cell>
		  <Table.Cell>
        <Button
              circular
              color='red'
              size='small'
              icon='trash'
              onClick={() => handleDeleteBudget(budget.budgetId)}>
        </Button>
		  </Table.Cell>
		</Table.Row>
      )
    })
  }

  return (
    <>
      <h1>Search by Category</h1>
      <Form onSubmit={handleSearchCategory}>
        <Input
          action={{ icon: 'search' }}
          name='categorySearch'
          placeholder='Search by Category'
          value={categorySearch}
          onChange={handleInputChange}
        />
      </Form>
      <p></p>
      <Form onSubmit={handleUpdateBudget}>
      <h1>Update Budget ID: {budgetId}</h1>
      <Form.Group>
        <Form.Input
            name='amount'
            width={4}
            placeholder={amount}
            onChange={handleInputChange}
            value={amount}
        />
        <Dropdown
          name='categoryName'
          placeholder={categoryName}
          selection
          onChange={handleInputChange}
          options={categoryNameOptions}
          value={categoryName}
        />
        <Form.Input
          name='description'
          width={7}
          placeholder={description}
          onChange={handleInputChange}
          value={description}
        />
        <Button 
          icon disabled={createBtnDisabled}
          width={4}>
          Update
        </Button>
      </Form.Group>
      </Form>
      <Table compact striped selectable>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell width={2}>Budget ID</Table.HeaderCell>
            <Table.HeaderCell width={2}>Category Name</Table.HeaderCell>
            <Table.HeaderCell width={1}>Amount (SGD)</Table.HeaderCell>
			      <Table.HeaderCell width={3}>Description</Table.HeaderCell>
            <Table.HeaderCell width={3}>Date Created</Table.HeaderCell>
			      <Table.HeaderCell width={3}>Date Updated</Table.HeaderCell>
            <Table.HeaderCell width={1}></Table.HeaderCell>
            <Table.HeaderCell width={1}></Table.HeaderCell>
          </Table.Row>
        </Table.Header>
        <Table.Body>
          {budgetList}
        </Table.Body>
      </Table>
    </>
  )
}

export default BudgetsTable