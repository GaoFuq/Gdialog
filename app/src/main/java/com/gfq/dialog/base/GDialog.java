package com.gfq.dialog.base;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;

/**
 * @created GaoFuq
 * @Date 2020/7/8 16:06
 * @Descaption
 */
public interface GDialog<T extends ViewDataBinding> {
     T bindView(@LayoutRes int layout);
     void show();
     void dismiss();
}
