package _04_order.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import _05_product.model.ProductFormatBean;

public class ShoppingCart {

	private Map<String, Map<OrderItemBean, Set<ProductFormatBean>>> cart = new LinkedHashMap<>();

	public ShoppingCart() {
	}

	public Map<String, Map<OrderItemBean, Set<ProductFormatBean>>> getContent() {
		return cart;
	}

	// 加入購物車(Map)
	public void addToCart(String productFormatIdStr, OrderItemBean oib, Set<ProductFormatBean> formats) {
		if (oib.getQuantity() <= 0) {
			return;
		}
		String productFormatId = productFormatIdStr.substring(1);
		System.out.println("(ShoppingCart)productFormatId= " + productFormatId);
		// 如果購物車裡沒有此規格商品 => 加進購物車的map
		if (cart.get("y" + productFormatId) == null && cart.get("n" + productFormatId) == null) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = new LinkedHashMap<OrderItemBean, Set<ProductFormatBean>>();
			orderMap.put(oib, formats);
			cart.put(productFormatIdStr, orderMap);
		} else {
			// 如果有的話 取得在購物車裡該商品的ID
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatId);
			OrderItemBean oiBean = orderMap.keySet().iterator().next();
			// 並把原有的數量加進來
			oiBean.setQuantity(oib.getQuantity() + oiBean.getQuantity());
		}
	}

	// 更動購物車內的商品數量
	public boolean changeQty(String productFormatIdStr, int newQty) {
		if (cart.get(productFormatIdStr) != null) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatIdStr);
			OrderItemBean bean = orderMap.keySet().iterator().next();
			bean.setQuantity(newQty);
			return true;
		} else {
			return false;
		}
	}

	// 更動購物車內的商品規格
	public boolean changeFormat(String productFormatIdStr, String content1, String content2) {
		if (cart.get(productFormatIdStr) != null) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatIdStr);
			OrderItemBean bean = orderMap.keySet().iterator().next();
			if (content1 != null) {
				bean.setFormatContent1(content1);
			}
			if (content2 != null) {
				bean.setFormatContent2(content2);
			}
			return true;
		} else {
			return false;
		}
	}

	// 更動購物車內的商品選取項(單項)
	public boolean changeChecked(String productFormatIdStr, String choose) {
		if (cart.get(productFormatIdStr) != null) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatIdStr);
			// 將此項從map中移除，更換key值後重新加入
			cart.remove(productFormatIdStr);
			if (choose == "true") {
				productFormatIdStr.replace('n', 'y');
			} else {
				productFormatIdStr.replace('y', 'n');
			}
			cart.put(productFormatIdStr, orderMap);
			return true;
		} else {
			return false;
		}
	}

	// 更動購物車內的商品選取項(全選)
	public void changeAllChecked(String chooseAll) {
		Set<String> productFormatIdStrs = cart.keySet();
		for (String productFormatIdStr : productFormatIdStrs) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(productFormatIdStr);
			cart.remove(productFormatIdStr);
			if (chooseAll == "true") {
				productFormatIdStr.replace('n', 'y');
			} else {
				productFormatIdStr.replace('y', 'n');
			}
			cart.put(productFormatIdStr, orderMap);
		}
	}

	// 刪除購物車內的商品
	public int deleteProduct(String productFormatId) {
		if (cart.get(productFormatId) != null) {
			cart.remove(productFormatId);
			return 1;
		} else {
			return 0;
		}
	}

	public int getItemNumber() {
		return cart.size();
	}

	// 計算購物車內的商品價格加總
	public int getSubtotal() {
		Integer subTotal = 0;
		Set<String> set = cart.keySet();
		for (String n : set) {
			Map<OrderItemBean, Set<ProductFormatBean>> orderMap = cart.get(n);
			OrderItemBean oib = orderMap.keySet().iterator().next();
			Integer price = oib.getUnitPrice();
			Integer quantity = oib.getQuantity();
			subTotal += price * quantity;
		}
		return subTotal;
	}

	public int addProductId(int productId) {
		return productId;
	}

}
