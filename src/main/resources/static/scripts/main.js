function decrement() {
    document.getElementById('quantity-input').stepDown();
}

function increment() {
    document.getElementById('quantity-input').stepUp();
}

const quantityInput = document.getElementById('quantity-input');
const subtotal = document.getElementById('subtotal');

const inputHandler = function(e) {
    subtotal.innerHTML = e.target.value;
}

quantityInput.addEventListener('input', inputHandler);
quantityInput.addEventListener('propertyChange', inputHandler);