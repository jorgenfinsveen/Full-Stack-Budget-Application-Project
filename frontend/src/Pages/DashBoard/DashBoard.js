import { BarChart } from "./BarChart";
import './DashBoard.css';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import dayjs from 'dayjs';
import { LocalizationProvider } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

export function DashBoard() {

    return( 
        <article>
            <header>
                <div id="title">
                    <h1>Dashboard</h1>
                </div>
                <div id="date">
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DatePicker label="" defaultValue={dayjs('2023-03-25')}/>
                    </LocalizationProvider>
                </div>
            </header>
            <hr/>
            <div id="budget-balance">
                <h2>Total available balance</h2>
                <p>Kr 20 000</p>
            </div>
            <hr/>
            <div id="budget-summary">
                <div id="budget-summary-income">
                    <h3>Income</h3>
                    <p>Kr 200</p>
                </div>
                <div id="budget-summary-expense">
                    <h3>Expense</h3>
                    <p>Kr 4 500</p>
                </div>
                <div id="budget-summary-remaining">
                    <h3>Budget Remaining</h3>
                    <p>Kr 15 700</p>
                </div>
            </div>
            <hr/>
            <h2 id="bar-title">Income & Expense</h2>
            <div id="barchart">
                <BarChart/>
            </div>
        </article>
    );
}