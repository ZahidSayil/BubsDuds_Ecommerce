function add_to_cart(id,name,price)
{
	let cart=localStorage.getItem("cart");
	if(cart==null){
		let products=[];
		let product={productId:id,ProductName:name,productQuantity:1,productPrice:price}
		
		products.push(product);
		localStorage.setItem("cart",JSON.stringify(products));
	  console.log("Product is added for first time");
		showToast("Item added to cart")
	}
	else{
		let pcart=JSON.parse(cart);
		let oldProduct=pcart.find((item)=>item.productId==id)
		if(oldProduct){
			oldProduct.productQuantity=oldProduct.productQuantity+1;
			pcart.map((item)=>{
				if(item.productId==oldProduct.productId)
				{
					item.productQuantity=oldProduct.productQuantity;
				}
			})
			localStorage.setItem("cart",JSON.stringify(pcart));
		  console.log("Product quantity is increased");
		showToast(oldProduct.ProductName+" quantity is increased");
		}else
		{
			let product={productId:id,ProductName:name,productQuantity:1,productPrice:price}
			pcart.push(product);
			localStorage.setItem("cart",JSON.stringify(pcart));
				  console.log("New Product is added ");
			showToast("Item added to cart")
		}
	}
	updateCart();
}


function updateCart(){
	let cartString=localStorage.getItem("cart");
	let cart=JSON.parse(cartString);
	if(cart==null || cart.length==0){
		console.log("cart is empty")
		$(".cart-items").html("( 0 )")
		$(".cart-body").html("<h4>Cart does not have any items</h4>");
		$(".checkout-btn").addClass('disabled');
	}
	else
	{
		console.log(cart);
		$(".cart-items").html(`(${cart.length})`);
		$(".checkout-btn").removeClass('disabled');
		let table=`
		<table class='table'>
		<thead class='thread-light'>
			<tr>
			<th>Item Name</th>
			<th>Price </th>
			<th>Quantity</th>
			<th>Total Price</th>
			<th>Action</th>
			</tr>
		</thead>
		`;
		let totalPrice=0;
		cart.map((item)=>{
			table+=`
			<tr>
			<td>${item.ProductName}</td>
			<td>${item.productPrice}</td>
			<td>${item.productQuantity}</td>
			<td>${item.productQuantity*item.productPrice}</td>
			<td><button onclick='deleteItemFromCart(${item.productId})' class='btn btn-danger btn-sm'>Remove</button></td>
			</tr>
			
			`
			totalPrice+=item.productPrice*item.productQuantity;
			
			
		})
		
		table=table+`
		<tr><td colspan='5'class='text-right font-weight-bold m-5'>Total Price:${totalPrice} </td></tr>
		</table>`
		$(".cart-body").html(table);
	}
	
}

function deleteItemFromCart(pid){
	let cart=JSON.parse(localStorage.getItem('cart'));
	let newcart=cart.filter((item)=>item.productId!=pid)
	localStorage.setItem('cart',JSON.stringify(newcart));
	updateCart();
	showToast("Item removed from cart")
}


$(document).ready(function(){
	updateCart();
})

  function showToast(content){
        $("#toast").addClass("display");
		$("#toast").html(content);
        setTimeout(()=>{
            $('#toast').removeClass("display")   ;
        },2000);
    }