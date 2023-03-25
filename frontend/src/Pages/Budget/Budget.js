import './Budget.css';

export function Budget() {
    return( 
        <article>
            <header>
                <h1>Budget</h1>
            </header>
            <hr/>
            <div id="budget-limit">
                <h2>Limit</h2>
                <p>Excluding your fixed monthly spendings such as bills</p>
                <h3>- Kr 10 000 +</h3>
            </div>
            <hr/>
            <div id="monthly-category-budget">
                <h2>Monthly Category Budget</h2>
                <table>
                    <thead>
                        <th>Name:   </th>
                        <th>Limit:   </th>
                        <th>Category:   </th>
                    </thead>
                </table>
            </div>
        </article>
    );
}