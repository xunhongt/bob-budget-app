import React, { Component } from 'react'
import { Statistic, Grid, Container, Image, Segment, Dimmer, Loader } from 'semantic-ui-react'
import logo from '../../images/bob-budget-app.png'
import banner from '../../images/banner.png'
import { budgetApi } from '../misc/BudgetApi'
import { handleLogError } from '../misc/Helpers'
import AuthContext from '../context/AuthContext'

class Home extends Component {
  static contextType = AuthContext

  state = {
    userCount: 0,
    categoryCount: 0,
    savings: 0,
    expenditure: 0,
    income: 0,
    statsColumn: '',
    isAdmin: false,
    isLoading: false
  }

  async componentDidMount() {
    const Auth = this.context
    const user = Auth.getUser()
    const isAdmin = user.role === 'ADMIN'

    this.setState({ isAdmin })
    this.setState({ isLoading: true })

    try {
      if (isAdmin) {

        let response = await budgetApi.userCount(user)
        const userCount = response.data
  
        response = await budgetApi.categoryCount(user)
        const categoryCount = response.data
  
        this.setState({ userCount, categoryCount })

        this.handleAdminsColumn(userCount, categoryCount)

      } else {
        let response = await budgetApi.getExpenditure(user)
        const expenditure = response.data

        response = await budgetApi.getIncome(user)
        const income = response.data

        response = await budgetApi.getSavings(user)
        const savings = response.data
  
        this.setState({ expenditure, income, savings })

        this.handleUserColumn(expenditure, income, savings)
      }
    } catch (error) {
      handleLogError(error)
    } finally {
      this.setState({ isLoading: false })
    }
  }

  handleAdminsColumn = (userCount, categoryCount ) => {
    let statsColumn 
      statsColumn = (
        <Grid stackable columns={2}>
        <Grid.Row>
          <Grid.Column textAlign='center'>
            <Segment color='blue'>
              <Statistic>
                <Statistic.Value>{userCount}</Statistic.Value>
                <Statistic.Label>Users</Statistic.Label>
              </Statistic>
            </Segment>
          </Grid.Column>
          <Grid.Column textAlign='center'>
            <Segment color='blue'>
              <Statistic>
                <Statistic.Value>{categoryCount}</Statistic.Value>
                <Statistic.Label>Categories</Statistic.Label>
              </Statistic>
            </Segment>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      )
    this.setState({ statsColumn })
  }

  handleUserColumn = (expenditure, income, savings) => {
    let statsColumn 
      statsColumn = (
        <Grid stackable columns={1}>
          <Grid.Row>
            <Grid.Column textAlign='center'>
                  <Segment color='blue'>
                    <Statistic>
                      <Statistic.Value>{expenditure.toFixed(2)}</Statistic.Value>
                      <Statistic.Label>Expenditure</Statistic.Label>
                    </Statistic>
                  </Segment>
            </Grid.Column>
            <Grid.Column textAlign='center'>
                  <Segment color='blue'>
                    <Statistic>
                      <Statistic.Value>{income.toFixed(2)}</Statistic.Value>
                      <Statistic.Label>Income</Statistic.Label>
                    </Statistic>
                  </Segment>
            </Grid.Column>
            <Grid.Column textAlign='center'>
                  <Segment color='blue'>
                    <Statistic>
                      <Statistic.Value>{savings.toFixed(2)}</Statistic.Value>
                      <Statistic.Label>Savings</Statistic.Label>
                    </Statistic>
                  </Segment>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      )
    this.setState({ statsColumn })
  }

  render() {
    const { isLoading } = this.state
    if (isLoading) {
      return (
        <Segment basic style={{ marginTop: window.innerHeight / 2 }}>
          <Dimmer active inverted>
            <Loader inverted size='huge'>Loading</Loader>
          </Dimmer>
        </Segment>
      )
    } else {
      const { statsColumn } = this.state
      return (
        <Container text>
          {statsColumn}
          <Image src={logo} />
          <Image src={banner} />
        </Container>
      )
    }
  }
}

export default Home