import axios from 'axios'
import { config } from '../../Constants'

export const budgetApi = {
  authenticate,
  signup,
  getUsers,
  getUser,
  updateUser,
  deleteUser,
  userCount,
  addCategory,
  getCategories,
  getCategory,
  updateCategory,
  deleteCategory,
  categoryCount,
  addBudget,
  getBudget,
  getBudgets,
  getBudgetsByCategory,
  getAllBudgets,
  updateBudget,
  deleteBudget,
  getSavings,
  getExpenditure,
  getIncome,
  getCategorySum
}

//---------- Authentication -----------

function authenticate(username, password) {
  return instance.post('/auth/authenticate', { username, password }, {
    headers: { 'Content-type': 'application/json' }
  })
}

function signup(user) {
  return instance.post('/auth/signup', user, {
    headers: { 'Content-type': 'application/json' }
  })
}

//---------- Users -----------

function getUsers(user) {
  return instance.get('/users', {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getUser(user, username) {
  return instance.get(`/users/${username}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function updateUser(user, userDetails) {
  return instance.put('/users', userDetails, {
    headers: { 
      'Content-type': 'application/json',
      'Authorization': basicAuth(user) 
    }
  })
}

function deleteUser(user, username) {
  return instance.delete(`/users/${username}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function userCount(user) {
  return instance.get('/totalusers', {
    headers: { 'Authorization': basicAuth(user) }
  })
}

//---------- Categories -----------

function addCategory(user, category) {
  return instance.post('/categories', category, {
    headers: {
      'Content-type': 'application/json',
      'Authorization': basicAuth(user)
    }
  })
}

function getCategories(user) {
  return instance.get('/categories', {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getCategory(user, categoryId) {
  return instance.get(`/categories/${categoryId}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function updateCategory(user, categoryId, category) {
  return instance.put(`/categories/${categoryId}`, category, {
    headers: { 
      'Content-type': 'application/json',
      'Authorization': basicAuth(user) 
    }
  })
}

function deleteCategory(user, categoryId) {
  return instance.delete(`/categories/${categoryId}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function categoryCount(user) {
  return instance.get('/totalcategories', {
    headers: { 'Authorization': basicAuth(user) }
  })
}

//---------- Budgets -----------

function addBudget(user, budget) {
  return instance.post('/budgets', budget, {
    headers: {
      'Content-type': 'application/json',
      'Authorization': basicAuth(user)
    }
  })
}

function getBudgets(user) {
  return instance.get('/budgets', {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getBudget(user, budgetId) {
  return instance.get(`/budget/${budgetId}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getBudgetsByCategory(user, categoryId) {
  return instance.get(`/budgets/${categoryId}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function getAllBudgets(user) {
  return instance.get('/allbudgets', {
    headers: { 'Authorization': basicAuth(user) }
  })
}

function updateBudget(user, budgetId, budget) {
  return instance.put(`/budget/${budgetId}`, budget, {
    headers: { 
      'Content-type': 'application/json',
      'Authorization': basicAuth(user) 
    }
  })
}

function deleteBudget(user, budgetId) {
  return instance.delete(`/budget/${budgetId}`, {
    headers: { 'Authorization': basicAuth(user) }
  })
}

//---------- Budget Count -----------

function getSavings(user) {
  return instance.get('/budgetcount', {
    headers: {
      'Content-type': 'application/json',
      'Authorization': basicAuth(user)
    }
  })
}

function getExpenditure(user) {
  return instance.get('/budgetcount/expenditure', {
    headers: {
      'Content-type': 'application/json',
      'Authorization': basicAuth(user)
    }
  })
}

function getIncome(user) {
  return instance.get('/budgetcount/income', {
    headers: {
      'Content-type': 'application/json',
      'Authorization': basicAuth(user)
    }
  })
}

function getCategorySum(user, categoryName) {
  return instance.get(`/budgetcount/type/${categoryName}`, {
    headers: { 
      'Content-type': 'application/json',
      'Authorization': basicAuth(user) 
    }
  })
}

// -- Axios

const instance = axios.create({
  baseURL: config.url.API_BASE_URL
})

// -- Helper functions

function basicAuth(user) {
  return `Basic ${user.authdata}`
}