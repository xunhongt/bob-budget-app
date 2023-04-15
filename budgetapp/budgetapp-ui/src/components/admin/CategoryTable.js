import React from 'react'
import { Form, Dropdown, Button, Table } from 'semantic-ui-react'

function CategoryTable({ categoryId,
                         categoryName,
                         categoryType, 
                         categories, 
                         handleInputChange,
                         setUpdateCategoryForm,
                         handleDeleteCategory, 
                         handleUpdateCategory, 
                         handleCategoryType }) {

  const categoryTypeOptions = [
    { key: 'expenditure', value: 'E', text: 'Expenditure' },
    { key: 'income', value: 'I', text: 'Income' }
  ]
  
  const createBtnDisabled = categoryName === '' || categoryType === ''

  let categoryList
  if (categories.length === 0) {
    categoryList = (
      <Table.Row key='no-categories'>
        <Table.Cell collapsing textAlign='center' colSpan='4'>No Categories</Table.Cell>
      </Table.Row>
    )
  } else {
    categoryList = categories.map(category => {
      return (
        <Table.Row key={category.categoryId}>
          <Table.Cell>{category.categoryId}</Table.Cell>
          <Table.Cell>{category.categoryName}</Table.Cell>
          <Table.Cell>{handleCategoryType(category.categoryType)}</Table.Cell>
          <Table.Cell> 
            <Button
                onClick={() => setUpdateCategoryForm(category)}>
              Update
            </Button>
          </Table.Cell>
          <Table.Cell collapsing>
            <Button
              circular
              color='red'
              size='small'
              icon='trash'
              onClick={() => handleDeleteCategory(category.categoryId)}>
            </Button>
          </Table.Cell>
        </Table.Row>
      )
    })
  }

  return (
    <>
      <Form onSubmit={handleUpdateCategory}>
      <h1>Update Category: {categoryName}</h1>
      <Form.Group>
        <Form.Input
          name='categoryName'
          placeholder={categoryName}
          onChange={handleInputChange}
          value={categoryName}
        />
        <Dropdown
          name='categoryType'
          placeholder='Select Category Type'
          selection
          onChange={handleInputChange}
          options={categoryTypeOptions}
          value={categoryType}
        />
        <Button 
          icon disabled={createBtnDisabled}
          width={4}>
          Update
        </Button>
      </Form.Group>
      </Form>
      <p></p>
      <Table compact striped selectable>
        <Table.Header>
          <Table.Row>
            <Table.HeaderCell width={2}>Category Id</Table.HeaderCell>
            <Table.HeaderCell width={4}>Category Name</Table.HeaderCell>
            <Table.HeaderCell width={4}>Category Type</Table.HeaderCell>
            <Table.HeaderCell width={1}/>
            <Table.HeaderCell width={1}/>
          </Table.Row>
        </Table.Header>
        <Table.Body>
          {categoryList}
        </Table.Body>
      </Table>
    </>
  )
}

export default CategoryTable