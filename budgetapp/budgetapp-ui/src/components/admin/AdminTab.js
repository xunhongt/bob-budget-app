import React from 'react'
import { Tab } from 'semantic-ui-react'
import UserTable from './UserTable'
import CategoryTable from './CategoryTable'
import CategoryAddForm from './CategoryAddForm'

function AdminTab(props) {
  const { handleInputChange } = props
  const { isUsersLoading, users, userUsernameSearch, handleUpdateUser, handleDeleteUser, handleSearchUser } = props
  const { username, password, email, firstName, lastName } = props
  const { isCategoriesLoading, categories, categoryName, categoryType, setUpdateUserForm } = props 
  const { setUpdateCategoryForm, handleAddCategory, handleDeleteCategory, handleUpdateCategory, handleCategoryType } = props

  const panes = [
    {
      menuItem: { key: 'users', icon: 'users', content: 'Users' },
      render: () => (
        <Tab.Pane loading={isUsersLoading}>
          <UserTable
            users={users}
            username={username}
            password={password}
            email={email}
            firstName={firstName}
            lastName={lastName}
            setUpdateUserForm={setUpdateUserForm}
            userUsernameSearch={userUsernameSearch}
            handleInputChange={handleInputChange}
            handleUpdateUser={handleUpdateUser}
            handleDeleteUser={handleDeleteUser}
            handleSearchUser={handleSearchUser}
          />
        </Tab.Pane>
      )
    },
    {
      menuItem: { key: 'category', icon: 'book', content: 'Categories List' },
      render: () => (
        <Tab.Pane loading={isCategoriesLoading}>
          <CategoryTable
            categories={categories}
            categoryName={categoryName}
            categoryType={categoryType}
            handleCategoryType={handleCategoryType}
            handleInputChange={handleInputChange}
            setUpdateCategoryForm={setUpdateCategoryForm}
            handleUpdateCategory={handleUpdateCategory}
            handleDeleteCategory={handleDeleteCategory}
          />
        </Tab.Pane>
      )
    },
    {
      menuItem: { key: 'addCategory', icon: 'plus', content: 'Add Category' },
      render: () => (
        <Tab.Pane loading={isCategoriesLoading}>
          <CategoryAddForm
            categories={categories}
            categoryName={categoryName}
            categoryType={categoryType}
            handleInputChange={handleInputChange}
            handleAddCategory={handleAddCategory}
            handleCategoryType={handleCategoryType}
          />
        </Tab.Pane>
      )
    }
  ]

  return (
    <Tab menu={{ attached: 'top' }} panes={panes} />
  )
}

export default AdminTab