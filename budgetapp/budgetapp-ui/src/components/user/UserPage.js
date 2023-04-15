import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
import { Container } from 'semantic-ui-react'
import AuthContext from '../context/AuthContext'
import { budgetApi } from '../misc/BudgetApi'
import UserTab from './UserTab'
import { handleLogError } from '../misc/Helpers'

class UserPage extends Component {
  static contextType = AuthContext
  
  state = {
    user: '',
    budgets: [],
    budgetId: '',
    amount: '',
    description: '',
    categories: [],
    categorySearch: '',
    categoryName: '',
    isUser: true,
    isBudgetsLoading: false,
    isCategoriesLoading: false
  }

  componentDidMount() {
    const Auth = this.context
    const user = Auth.getUser()
    const isUser = user.role === 'USER'
    this.setState({ user, isUser })

    this.handleGetBudgets()
    this.handleGetCategories()
  }

  // Handler Function that Updates the displayed value when user inputs something 

  handleInputChange = (e, { name, value }) => {
    this.setState({ [name]: value })
  }

  // Handler Function that Updates the displayed value when user inputs something 

  clearBudgetForm = () => {
    this.setState({
      categoryName: '',
      amount: '',
      description: ''
    })
  }

  setUpdateBudgetForm = (budget) => {
    this.setState({
      budgetId: budget.budgetId,
      categoryId: budget.categoryId,
      amount: budget.amount,
      description: budget.description
    })

    console.log(budget.budgetId)
  }

  //------------ Categories -----------------

  /* 
    Handler Function that calls the getCategories API in budgetapp-server 
      - Gets the list of Categories that are currently available
  */

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

  /* 
    Handler Function that calls the getBudgets API in budgetapp-server 
      - Gets the list of Budgets submitted by the User 
  */

  handleGetBudgets = () => {
    const Auth = this.context
    const user = Auth.getUser()

    this.setState({ isBudgetsLoading: true })
    budgetApi.getBudgets(user)
      .then(response => {
        this.setState({ budgets: response.data })
      })
      .catch(error => {
        handleLogError(error)
      })
      .finally(() => {
        this.setState({ isBudgetsLoading: false })
      })
  }

  handleGetBudget = (budgetId) => {
    const Auth = this.context
    const user = Auth.getUser()

    this.setState({ isBudgetsLoading: true })
    budgetApi.getBudget(user, budgetId)
      .then(response => {
        this.setState({ categoryName: response.data.categoryName, 
                        amount: response.data.amount, 
                        description: response.data.description })

      })
      .catch(error => {
        handleLogError(error)
      })
      .finally(() => {
        this.setState({ isBudgetsLoading: false })
      })
  }

  /* 
    Handler Function that calls the getBudgetsByCategory API in budgetapp-server 
      - Gets the list of Budgets based on a specific category 
  */

  handleSearchCategory = () => {
    const Auth = this.context
    const user = Auth.getUser()

    const text = this.state.categorySearch

    try {
      if (text.length === 0) {
        this.handleGetBudgets()
      } else {
        budgetApi.getBudgetsByCategory(user, text)
        .then(response => {
          const budgets = response.data
          this.setState({ budgets })
        })
      }
    } catch (e) {
        handleLogError(e)
        this.setState({ budgets: [] })
    }

  }

  /* 
    Handler Function that calls the addBudget API in budgetapp-server 
      - Adds the budget into the database
  */

  handleAddBudget = () => {
    const Auth = this.context
    const user = Auth.getUser()

    let { categoryName, amount, description } = this.state
    if (!(categoryName && amount && description)) {
      return
    }

    const budget = { "categoryName": categoryName, "amount": amount, "description": description}
    budgetApi.addBudget(user, budget)
      .then(() => {
        this.clearBudgetForm()
        this.handleGetBudgets()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  /* 
    Handler Function that calls the updateBudget API in budgetapp-server 
      - Updates the budget into the database
  */

  handleUpdateBudget = () => {
    const Auth = this.context
    const user = Auth.getUser()

    let { categoryName, amount, description, budgetId } = this.state
    if (!(categoryName && amount && description && budgetId )) {
      return
    }

    const budget = { "categoryName": categoryName, "amount": amount, "description": description}
    budgetApi.updateBudget(user, budgetId, budget)
      .then(() => {
        this.clearBudgetForm()
        this.handleGetBudgets()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  /* 
    Handler Function that calls the deleteBudget API in budgetapp-server 
      - Deletes the budget into the database
  */

  handleDeleteBudget = (budgetId) => {
    const Auth = this.context
    const user = Auth.getUser()

    budgetApi.deleteBudget(user, budgetId)
      .then(() => {
        this.handleGetBudgets()
      })
      .catch(error => {
        handleLogError(error)
      })
  }

  render() {
    if (!this.state.isUser) {
      return <Navigate to='/' />
    } else {
      const { 
              user,
              isBudgetsLoading,
              budgets,
              budgetBreakdown,
              budgetId,
              categories,
              categoryName,
              categorySum,
              amount,
              description, 
              categorySearch 
            } = this.state
      return (
        <Container>
          <UserTab
            user={user}
            isBudgetsLoading={isBudgetsLoading}
            categorySearch={categorySearch}
            budgets={budgets}
            budgetBreakdown={budgetBreakdown}
            budgetId={budgetId}
            categories={categories}
            amount={amount}
            description={description}
            categorySum={categorySum}
            categoryName={categoryName}
            handleInputChange={this.handleInputChange}
            handleSearchCategory={this.handleSearchCategory}
            handleAddBudget={this.handleAddBudget}
            handleGetBudget={this.handleGetBudget}
            handleUpdateBudget={this.handleUpdateBudget}
            setUpdateBudgetForm={this.setUpdateBudgetForm}
            handleDeleteBudget={this.handleDeleteBudget}
          />
        </Container>
      )
    }
  }
}

export default UserPage