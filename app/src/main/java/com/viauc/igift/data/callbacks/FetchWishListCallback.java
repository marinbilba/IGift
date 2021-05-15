package com.viauc.igift.data.callbacks;

import com.viauc.igift.model.WishList;

import java.util.List;

public interface FetchWishListCallback {
    void fetchedWishListOnSuccess(List<WishList> wishListList);
}
