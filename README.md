Here are 20 React.js interview questions with answers and explanations tailored for candidates with 2 years of experience:


---

1. What is React?

Answer: React is a JavaScript library developed by Facebook for building user interfaces, especially single-page applications. It allows developers to create reusable UI components.

Explanation: It efficiently updates and renders the right components when your data changes, using a virtual DOM.


---

2. What are components in React?

Answer: Components are the building blocks of a React application. They encapsulate logic and UI, and can be functional or class-based.

Explanation: Components help reuse code and make it easier to manage the UI.


---

3. What is JSX?

Answer: JSX stands for JavaScript XML. It allows writing HTML-like syntax in JavaScript files, which is then transformed to React.createElement calls.

Explanation: It makes writing and understanding the UI structure easier and more intuitive.


---

4. What is the virtual DOM?

Answer: The virtual DOM is a lightweight copy of the actual DOM. React uses it to optimize rendering by updating only the changed elements.

Explanation: Improves performance by minimizing direct DOM manipulations.


---

5. What is the difference between state and props?

Answer:

Props: Passed from parent to child, immutable.

State: Managed within the component, mutable.


Explanation: Props are for communication; state is for internal data management.


---

6. What are hooks in React?

Answer: Hooks are functions that let you use state and lifecycle features in functional components.

Explanation: Examples include useState, useEffect, useRef.


---

7. What is useState?

Answer: A hook that adds state to functional components.

const [count, setCount] = useState(0);

Explanation: It returns a stateful value and a function to update it.


---

8. What is useEffect used for?

Answer: It performs side effects in functional components like fetching data, setting up subscriptions, etc.

Explanation: It replaces lifecycle methods like componentDidMount, componentDidUpdate.


---

9. What is the difference between controlled and uncontrolled components?

Answer:

Controlled: Form data is handled by React state.

Uncontrolled: Form data is handled by the DOM.


Explanation: Controlled components provide better control over form behavior.


---

10. How does key help in lists?

Answer: key helps React identify which items changed, are added, or removed.

Explanation: Keys improve rendering performance in lists.


---

11. What is prop drilling?

Answer: Passing data through multiple levels of components unnecessarily.

Explanation: Can be avoided using context or state management libraries.


---

12. What is context API?

Answer: A way to pass data through the component tree without manually passing props.

Explanation: Useful for global data like themes or auth status.


---

13. What are fragments in React?

Answer: <React.Fragment> or <> lets you group elements without adding extra DOM nodes.

Explanation: Helps in clean markup without unnecessary wrapper tags.


---

14. What is a higher-order component (HOC)?

Answer: A function that takes a component and returns a new component with added features.

Explanation: Used for code reuse like authentication or theming.


---

15. What is lazy loading in React?

Answer: A technique to load components only when needed using React.lazy and Suspense.

Explanation: Improves performance by reducing initial load time.


---

16. How do you handle forms in React?

Answer: Using controlled components, React state manages form inputs and handles changes via onChange.

Explanation: Allows validation and dynamic form behavior.


---

17. What is the difference between useEffect and useLayoutEffect?

Answer:

useEffect: Runs after rendering.

useLayoutEffect: Runs synchronously after DOM mutations but before the browser paints.


Explanation: Use useLayoutEffect only when necessary to avoid flickering.


---

18. What is memoization in React?

Answer: A technique to optimize performance by caching the result of expensive functions/components using React.memo or useMemo.

Explanation: Prevents unnecessary re-renders.


---

19. What is the role of Redux or Zustand in React?

Answer: State management libraries that help manage complex app-wide state outside of React's component tree.

Explanation: Useful when many components need access to the same data.


---

20. What is reconciliation in React?

Answer: The process of updating the DOM by comparing the new virtual DOM with the previous one.

Explanation: React determines the minimum number of changes needed and applies them efficiently.


---

Would you like a downloadable PDF of these questions?