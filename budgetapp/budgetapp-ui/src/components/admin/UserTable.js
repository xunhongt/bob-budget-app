import React from 'react'
import { Form, Button, Input, Table } from 'semantic-ui-react'

function UserTable({ users, 
                     username, 
                     password, 
                     firstName, 
                     lastName, 
                     email,
                     setUpdateUserForm,
                     userUsernameSearch, 
                     handleInputChange, 
                     handleDeleteUser, 
                     handleUpdateUser, 
                     handleSearchUser }) {

  const createBtnDisabled = username === '' || password === '' || email === '' || firstName === '' || lastName === '' 

  let userList
  if (users.length === 0) {
    userList = (
      <Table.Row key='no-user'>
        <Table.Cell collapsing textAlign='center' colSpan='6'>No user</Table.Cell>
      </Table.Row>
    )
  } else {
    console.log(username)
    userList = users.map(user => {
      return (
        <Table.Row key={user.username}>
          <Table.Cell>{user.username}</Table.Cell>
          <Table.Cell>{user.firstName}</Table.Cell>
          <Table.Cell>{user.lastName}</Table.Cell>
          <Table.Cell>{user.email}</Table.Cell>
          <Table.Cell>{user.role}</Table.Cell>
          <Table.Cell> 
            <Button
                onClick={() => setUpdateUserForm(user)}>
              Update
            </Button>
          </Table.Cell>
          <Table.Cell collapsing>
            <Button
              circular
              color='red'
              size='small'
              icon='trash'
              disabled={user.username === 'admin'}
              onClick={() => handleDeleteUser(user.username)}
            />
          </Table.Cell>
        </Table.Row>
      )
    })
  }

  return (
    <>
      <Form onSubmit={handleSearchUser}>
        <Input
          action={{ icon: 'search' }}
          name='userUsernameSearch'
          placeholder='Search by Username'
          value={userUsernameSearch}
          onChange={handleInputChange}
        />
      </Form>
      <p></p>
      <Form onSubmit={handleUpdateUser}>
      <h1>Update User: {username}</h1>
      <Form.Group>
        <Form.Input
          name='username'
          placeholder={username}
          onChange={handleInputChange}
          value={username}
        />
        <Form.Input
          name='password'
          placeholder={password}
          type='password'
          onChange={handleInputChange}
          value={password}
        />
        <Form.Input
          name='firstName'
          placeholder={firstName}
          onChange={handleInputChange}
          value={firstName}
        />
        <Form.Input
          name='lastName'
          placeholder={lastName}
          onChange={handleInputChange}
          value={lastName}
        />
        <Form.Input
          name='email'
          placeholder={email}
          onChange={handleInputChange}
          value={email}
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
            <Table.HeaderCell width={1}>Username</Table.HeaderCell>
            <Table.HeaderCell width={3}>First Name</Table.HeaderCell>
            <Table.HeaderCell width={4}>Last Name</Table.HeaderCell>
            <Table.HeaderCell width={5}>Email</Table.HeaderCell>
            <Table.HeaderCell width={2}>Role</Table.HeaderCell>
            <Table.HeaderCell width={1}/>
            <Table.HeaderCell width={1}/>
          </Table.Row>
        </Table.Header>
        <Table.Body>
          {userList}
        </Table.Body>
      </Table>
    </>
  )
}

export default UserTable