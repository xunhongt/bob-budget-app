import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
import { Container } from 'semantic-ui-react'
import AuthContext from '../context/AuthContext'
import { budgetApi } from '../misc/BudgetApi'
import AdminTab from './AdminTab'
import { handleLogError } from '../misc/Helpers'

class AdminPage extends Component {
  static contextType = AuthContext

  state = {
    users: [],
    categories: [],
    categoryId: '',
    categoryName: '',
    categoryType: '',
    username: '',
    firstName: '',
    lastName: '',
    password: '',
    email: '',
    userUsernameSearch: '',
    isAdmin: true,
    isUsersLoading: false,
    isCategoriesLoading: false,
  }

  componentDidMount() {
    const Auth = this.context
    const user = Auth.getUser()
    const isAdmin = user.role === 'ADMIN'
    this.setState({ isAdmin })

    this.handleGetUsers()
    this.handleGetCategories()
  }

  handleInputChange = (e, { name, value }) => {
    this.setState({ [name]: value })
  }

  clearUserForm = () => {
    this.setState({
      username: '',
      firstName: '',
      lastName: '',
      password: '',
      email: ''
    })
  }

  
  clearCategoryForm = () => {
    this.setState({
      categoryName: '',
      categoryType: ''
    })
  }

  setUpdateUserForm = (user) => {
    this.setState({
      username: user.username,
      password: user.password,
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email
    })
/*
    console.log(user.username)
    console.log(user.password)
    console.log(user.firstName)
    console.log(user.lastName)
    console.log(user.email)
    */
  }

  setUpdateCategoryForm = (category) => {
    this.setState({
      categoryId: category.categoryId,
      categoryName: category.categoryName,
      categoryType: category.categoryType
    })
    console.log(category.categoryId)
    console.log(category.categoryName)
    console.log(category.categoryType)
  }



  handleGetUsers = () => {
    const Auth = this.context
    const user = Auth.getUser()

    this.setState({ isUsersLoading: true })
    budgetApi.getUsers(user)
      .then(response => {
        this.setState({ users: response.data })
      })
      .catch(error => {
        handleLogError(error)
      })
      .finally(() => {
        this.setState({ isUsersLoading: false })
      })
  }

  handleUpdateUser = () => {
    const Auth = this.context
    const user = Auth.getUser()

    let { username, firstName, lastName, password, email } = this.state
    if (!(username && firstName && lastName && password && email)) {
      return
    }

    const userDetails = { "username": username, "firstName": firstName, "lastName": lastName, "password": password, "email": email }
    budgetApi.updateUser(user, userDetails)
      .then(() => {
        this.clearUserForm()
        this.handleGetUsers()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  handleDeleteUser = (username) => {
    const Auth = this.context
    const user = Auth.getUser()

    budgetApi.deleteUser(user, username)
      .then(() => {
        this.handleGetUsers()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  handleSearchUser = () => {
    const Auth = this.context
    const user = Auth.getUser()

    const username = this.state.userUsernameSearch

    try {
      if (username.length === 0) {
        this.handleGetUsers()
      } else {
        budgetApi.getUser(user, username)
        .then(response => {
          const data = response.data
          const users = data instanceof Array ? data : [data]
          this.setState({ users })
        })
      }
    } catch (e) {
      handleLogError(e)
      this.setState({ users: [] })
    }
  }

  handleGetCategories = () => {
    const Auth = this.context
    const user = Auth.getUser()

    this.setState({ isCategoriesLoading: true })
    budgetApi.getCategories(user)
      .then(response => {
        this.setState({ categories: response.data })
      })
      .catch(error => {
        handleLogError(error)
      })
      .finally(() => {
        this.setState({ isCategoriesLoading: false })
      })
  }

  handleCategoryType = (categoryType) => {
    if (categoryType === 'E') {
      return "Expenditure"
    } else if (categoryType === 'I') {
      return "Income"
    }
  }

  handleDeleteCategory = (categoryId) => {
    const Auth = this.context
    const user = Auth.getUser()

    budgetApi.deleteCategory(user, categoryId)
      .then(() => {
        this.handleGetCategories()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  handleAddCategory = () => {
    const Auth = this.context
    const user = Auth.getUser()

    let { categoryName, categoryType } = this.state
    if (!(categoryName && categoryType)) {
      return
    }

    const category = { "categoryName": categoryName, "categoryType": categoryType }
    budgetApi.addCategory(user, category)
      .then(() => {
        this.clearCategoryForm()
        this.handleGetCategories()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  handleUpdateCategory = () => {
    const Auth = this.context
    const user = Auth.getUser()

    let { categoryId, categoryName, categoryType } = this.state
    if (!(categoryId && categoryName && categoryType )) {
      return
    }

    const category = { "categoryName": categoryName, "categoryType": categoryType }
    budgetApi.updateCategory(user, categoryId, category)
      .then(() => {
        this.clearCategoryForm()
        this.handleGetCategories()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  render() {
    if (!this.state.isAdmin) {
      return <Navigate to='/' />
    } else {
      const { 
        isUsersLoading, 
        users,
        username, 
        password, 
        email, 
        firstName, 
        lastName, 
        userUsernameSearch, 
        isCategoriesLoading, 
        categories, 
        categoryName, 
        categoryType } = this.state
      return (
        <Container>
          <AdminTab
            isUsersLoading={isUsersLoading}
            users={users}
            username={username}
            password={password}
            email={email}
            firstName={firstName}
            lastName={lastName}
            userUsernameSearch={userUsernameSearch}
            handleUpdateUser={this.handleUpdateUser}
            handleDeleteUser={this.handleDeleteUser}
            handleSearchUser={this.handleSearchUser}
            setUpdateUserForm={this.setUpdateUserForm}
            isCategoriesLoading={isCategoriesLoading}
            categories={categories}
            categoryName={categoryName}
            categoryType={categoryType}
            setUpdateCategoryForm={this.setUpdateCategoryForm}
            handleAddCategory={this.handleAddCategory}
            handleUpdateCategory={this.handleUpdateCategory}
            handleDeleteCategory={this.handleDeleteCategory}
            handleCategoryType={this.handleCategoryType}
            handleInputChange={this.handleInputChange}
          />
        </Container>
      )
    }
  }
}

export default AdminPage