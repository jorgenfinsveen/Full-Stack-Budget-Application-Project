import React, { useState, useEffect } from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Bar } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);


/**
 * BarChart component imported from ChartJS. It is used
 * to display all transactions associated with the given 
 * budget.
 * 
 * @param props.income  {Array} Income values represented by integers.
 * @param props.expense {Array} Expense values represented by integers.
 * @see https://react-chartjs-2.js.org/examples/vertical-bar-chart
 */
export function BarChart(props) {

  /** 
   * The BarChart is not intended to show labels, since there are only two bars
   * which already are identified by the label-field of their respective datasets.
   */
  const labels = [''];


  /**
   * The dataset which is being used for displaying the bars in the diagram.
   * It is supervised by the useState-hook, which ensures that the data is
   * updateable upon changes of the transactions which it displays.
   */
  const [bar, setBar] = useState(
    {
      labels,
      datasets: [
        {
          label: 'Income',
          data: labels.map(() => 0),
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expenses',
          data: labels.map(() => 0),
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ]
    }
  );



  /**
   * Returns an updated version of the dataset which are to be stored
   * in the hooked bar variable.
   * 
   * @param incomes An array of income values represented as integers.
   * @param expenses An array of expense values represented as integers.
   */
  const getData = (incomes, expenses) => {
    return {
      labels,
      datasets: [
        {
          label: 'Income',
          data: labels.map(() => incomes),
          backgroundColor: 'rgba(99, 255, 32, 0.5)',
        },
        {
          label: 'Expenses',
          data: labels.map(() => expenses),
          backgroundColor: 'rgba(255, 39, 32, 0.5)',
        },
      ],
    };
  };



  /**
   * React hook which updates the dataset which are to be displayed
   * in the BarChart upon changes of any of the values provided as
   * props to the component.
   */
  useEffect(() => {
    setBar(getData(props.income, props.expense));
    // eslint-disable-next-line
  }, [props, props.expense, props.income]);



  /**
   * Display options which configures the layout and mechanics of
   * the BarChart.
   */
  const options = {
    responsive: true,
    animation: {
      duration: 50
    },
    interaction: {
      intersect: false,
      mode: '',
    },
    tooltip: {
      enabled: false
    },
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: false,
        text: 'Chart.js Bar Chart',
      },
    },
  };
  


  return (
    <Bar 
      options={options} 
      data={bar} 
      width={"60rem"}
      height={"20rem"}
    />);
}
