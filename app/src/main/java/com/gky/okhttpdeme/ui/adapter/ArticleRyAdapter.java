package com.gky.okhttpdeme.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gky.okhttpdeme.R;
import com.gky.okhttpdeme.model.entity.ArticleInfo;
import com.gky.okhttpdeme.ui.listener.ArticleItemListener;

import java.util.List;

/**
 * Created by 凯阳 on 2016/6/27.
 */
public class ArticleRyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    implements View.OnClickListener, View.OnLongClickListener{

    private List<ArticleInfo> mArticles;

    private ArticleItemListener mItemListener;

    private Context mContext;

    private LayoutInflater mInflater;

    public ArticleRyAdapter(Context context, ArticleItemListener listener) {
        mContext = context;
        mItemListener = listener;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setArticles(List<ArticleInfo> datas){
        if(mArticles != null){
            mArticles.addAll(datas);
        }else{
            mArticles = datas;
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_articleinfo, parent, false);
        return new ItemArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemArticleViewHolder articleViewHolder = (ItemArticleViewHolder) holder;
        articleViewHolder.setOnClick(this);
        articleViewHolder.setOnLongClick(this);
        articleViewHolder.setTag(position);
        ArticleInfo articleInfo = mArticles.get(position);
        articleViewHolder.setContent(articleInfo.getDesc(), articleInfo.getWho(), articleInfo.isUsed());
    }

    @Override
    public int getItemCount() {
        return mArticles != null ? mArticles.size() : 0;
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        ArticleInfo articleInfo = mArticles.get(index);
        articleInfo.setUsed(false);
        mItemListener.OnItemClick(articleInfo);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    class ItemArticleViewHolder extends RecyclerView.ViewHolder{

        private TextView mContent;

        public ItemArticleViewHolder(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.tv_content);
        }

        public void setTag(int index){
            itemView.setTag(index);
        }

        public void setOnClick(View.OnClickListener listener){
            itemView.setOnClickListener(listener);
        }

        public void setOnLongClick(View.OnLongClickListener listener){
            itemView.setOnLongClickListener(listener);
        }

        public void setContent(String desc, String who, boolean used){
            String content = String.format("%s(%s)", desc, who);
            int first = content.indexOf("("), last = content.indexOf(")");
            SpannableStringBuilder builder = new SpannableStringBuilder(content);
            if(used) {
                builder.setSpan(new ForegroundColorSpan(Color.GRAY), first, last + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }else{
                mContent.setTextColor(Color.GRAY);
                builder.setSpan(new ForegroundColorSpan(Color.parseColor("#9C27B0")), 0, first, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            mContent.setText(builder);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{

        private ProgressBar mProgress;

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
