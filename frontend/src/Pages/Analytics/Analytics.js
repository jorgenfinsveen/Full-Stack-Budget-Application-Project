import { BarChart } from "./BarChart";
import { LineChart } from "./LineChart";
import './Analytics.css';

export function Analytics() {
    return( 
        <article>
            <header>
                <h1>Analytics</h1>
            </header>
            <hr/>
            <h2>All incomes & expenses</h2>
            <div id="linechart">
                <LineChart/>
            </div>
            <hr/>
            <h2>Categorized incomes & expenses</h2>
            <div id="barchart">
                <BarChart/>
            </div>
        </article>
    );
}