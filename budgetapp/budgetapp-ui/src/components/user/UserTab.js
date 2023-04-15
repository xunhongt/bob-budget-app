import { React }from 'react'
import { Tab } from 'semantic-ui-react'
import BudgetsTable from './BudgetsTable'
import BudgetAddForm from './BudgetAddForm'

function UserTab(props) {
  const { handleInputChange } = props
  const { isBudgetsLoading } = props
  const { 
          budgets,
          budgetId,
          categories, 
          categoryName,
          amount, 
          description, 
          categorySearch, 
          handleSearchCategory, 
          handleAddBudget,
          handleUpdateBudget,
          setUpdateBudgetForm,
          handleDeleteBudget } = props

  const panes = [
    {
      menuItem: { key: 'budget', icon: 'money', content: 'Budget List' },
      render: () => (
        <Tab.Pane loading={isBudgetsLoading}>
          <BudgetsTable
            categorySearch={categorySearch}
            budgets={budgets}
            budgetId={budgetId}
            categories={categories}
            categoryName={categoryName}
            amount={amount}
            description={description}
            handleInputChange={handleInputChange}
            handleSearchCategory={handleSearchCategory}
            handleAddBudget={handleAddBudget}
            handleUpdateBudget={handleUpdateBudget}
            handleDeleteBudget={handleDeleteBudget}
            setUpdateBudgetForm={setUpdateBudgetForm}
          />
        </Tab.Pane>
      )
    },
    {
      menuItem: { key: 'Add Budget', icon: 'plus', content: 'Add Budget' },
      render: () => (
        <Tab.Pane loading={isBudgetsLoading}>
          <BudgetAddForm
            categories={categories}
            categoryName={categoryName}
            amount={amount}
            description={description}
            handleInputChange={handleInputChange}
            handleAddBudget={handleAddBudget}
          />
        </Tab.Pane>
      )
    }
  ]

  return (
    <Tab menu={{ attached: 'top' }} panes={panes} />
  )
}

export default UserTab