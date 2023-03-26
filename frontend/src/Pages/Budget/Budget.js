import './Budget.css';
import { Slider } from './Slider';
import dayjs from 'dayjs';
import { DemoContainer, DemoItem } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DateRangePicker } from '@mui/x-date-pickers-pro/DateRangePicker';
import Button from '@mui/material/Button';

export function Budget() {
    return( 
        <article>
            <header>
                <h1>Budget</h1>
            </header>
            <hr/>
            <div id="expense-container">
                <h2 id="expense-container-label">Expense</h2>
                <form>
                    <label for="expense-form-name">Name:</label>
                    <input type="text" id="expense-form-name"/>
                    <br/>
                    <label for="expense-form-price">Price:</label>
                    <input type="text" id="expense-form-price"/>
                    <br/>
                    <label for="expense-form-category">Category:</label>
                    <input type="text" id="expense-form-category"/>
                </form>
                <br/>
                <Button variant = "contained">Add</Button>
                <table>
                    <tr>
                        <th style={{width: 10 + 'rem'}}>Name</th>
                        <th style={{width: 5 + 'rem'}}>Category</th> 
                        <th style={{width: 5 + 'rem'}}>Price</th>
                    </tr>
                    <tr>
                        <td>Company dinner</td>
                        <td>Food</td> 
                        <td class="red">KR 2312</td>
                    </tr>
                    <tr>
                        <td>Conference flight</td>
                        <td>Transport</td> 
                        <td class="red">KR 1393</td>
                    </tr>
                    <tr>
                        <td>Car coverage</td>
                        <td>Insurance</td> 
                        <td class="red">KR 4016</td>
                    </tr>
                </table>
            </div>
            <div id="income-container">
                <h2 id="income-container-label">Income</h2>
                <form>
                    <label for="income-form-name">Name:</label>
                    <input type="text" id="income-form-name"/>
                    <br/>
                    <label for="income-form-price">Price:</label>
                    <input type="text" id="income-form-price"/>
                    <br/>
                    <label for="income-form-category">Category:</label>
                    <input type="text" id="income-form-category"/>
                </form>
                <br/>
                <Button variant = "contained">Add</Button>
                <table>
                    <tr>
                        <th style={{width: 10 + 'rem'}}>Name</th>
                        <th style={{width: 5 + 'rem'}}>Category</th> 
                        <th style={{width: 5 + 'rem'}}>Price</th>
                    </tr>
                    <tr>
                        <td>Product sales</td>
                        <td>Product</td> 
                        <td class="green">KR 7312</td>
                    </tr>
                    <tr>
                        <td>Subscription payments</td>
                        <td>Subscription</td> 
                        <td class="green">KR 2393</td>
                    </tr>
                    <tr>
                        <td>Sales tip</td>
                        <td>Bonus</td> 
                        <td class="green">KR 416</td>
                    </tr>
                </table>
            </div>
            <div id="budget-limit">
                <h2 id="limit-label">Limit</h2>
                <Slider/>
                <br/><br/><br/>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <DemoContainer components={['DateRangePicker', 'DateRangePicker']}>
                        <DemoItem label="" component="DateRangePicker">
                            <DateRangePicker
                                defaultValue={[dayjs('2023-03-22'), dayjs('2023-05-16')]}
                            />
                        </DemoItem>      
                    </DemoContainer>
                </LocalizationProvider>
            </div>
            <div id="status-container">
                <h2 id="status-container-label">Staus</h2>
                <p>Total income: <i id="total-income"> KR 4262</i></p>
                <p>Total expense: <i id="total-expense"> KR 6758</i></p>
                <p>Net worth: <i id="net-worth"> KR 8924:</i></p>
                <p>Budget spendings: <i id="budget-spendings"> KR 2353 / 10 000</i></p>
                <p>Margin: <i id="margin"> KR 7547</i></p>
            </div>
        </article>
    );
}