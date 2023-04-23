import React, { useState, useEffect } from 'react';
import { Line } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';


ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

/**
 * LineChart component imported from ChartJS. It is used
 * to display all transactions associated with the given 
 * budget.
 * 
 * @param props.incomes  {Array} Income values represented by integers.
 * @param props.expenses {Array} Expense values represented by integers.
 * @param props.labels   {Array} Labels to be displayed represented by strings.
 * @see https://react-chartjs-2.js.org/examples/line-chart/
 */
export function LineChart(props) {

  /** 
   * Default array containing a single empty string to be displayed as labels
   * in the diagram.
   */
  const labels = [''];


  /**
   * The dataset which is being used for displaying the lines in the diagram.
   * It is supervised by the useState-hook, which ensures that the data is
   * updateable upon changes of the transactions, or the labels which it displays.
   */
  const [lines, setLines] = useState(
    {
      labels,
      datasets: [
        {
          label: 'Income',
          data: [0],
          borderColor: 'rgba(99, 255, 32, 1)',
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expense',
          data: [0],
          borderColor: 'rgba(255, 39, 32, 1)',
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ],
    }
  );
  

  
  /**
   * Returns an updated version of the dataset which are to be stored
   * in the hooked lines variable.
   * 
   * @param incomes  An array of income values represented as integers.
   * @param expenses An array of expense values represented as integers.
   * @param labels   An array of labels to be displayed represented by strings.
   */
  const getData = (labels, incomes, expenses) => {
    return {
      labels,
      datasets: [
        {
          label: 'Income',
          data: incomes,
          borderColor: 'rgba(99, 255, 32, 1)',
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expense',
          data: expenses,
          borderColor: 'rgba(255, 39, 32, 1)',
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ],
    };
  };
  


  /**
   * React hook which updates the dataset which are to be displayed
   * in the LineChart upon changes of any of the values provided as
   * props to the component.
   */
  useEffect( () => {
    setLines(getData(props.labels, props.incomes, props.expenses));
  }, [props, props.labels, props.incomes, props.expenses]);



  /**
   * Display options which configures the layout and mechanics of
   * the LineChart.
   */
  const options = {
    responsive: true,
    interaction: {
      intersect: false,
      duration: 10,
      mode: 'index',
    },
    animation: {
      duration: 50
    },
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: false,
        text: 'Chart.js Line Chart',
      },
    },
  };



  return (
    <Line 
      options={options} 
      data={lines} 
      width={"80rem"}
      height={"20rem"}
    />
  );
}
