package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import androidx.recyclerview.widget.RecyclerView;

// extends generic adapter untuk RecyclerView agar dpt menggunakan ViewHolder yg
// didefinisikan di dalam WordViewHolder


public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    // Menyimpan data di adapter
    // Membuat linkedlist di WordListAdapter dan dipanggil di mWordList
    private final LinkedList<String> mWordList;



    // Contructor WordListAdapter untuk menginisialisasi daftar kata dari kata
    // LayoutInflator reads a layout XML description and converts it into the corresponding View items
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        // Membuat View Holder untuk menampilkan Recycler View
        // itemView untuk menampilkan data
        // adapter untuk manage data dan tampilan Recycler View

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Posisi ketike Item di Klik.
            int mPosition = getLayoutPosition();

            // untuk akses yang terpengaruh di mWordList
            String element = mWordList.get(mPosition);

            // Ubah kata di mWordList
            mWordList.set(mPosition, "Clicked! " + element);
            // Notify the adapter, data berubah lalu update di Recycler View untuk menampilkan data
            mAdapter.notifyDataSetChanged();
        }
    }
    // Implement the constructor for WordListAdapter. The constructor needs to have a context parameter,
    // and a linked list of words with the app's data.
    // Method yg menginisiasi LayoutInflator untuk mInflater
    // dan untuk setmWordList
    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    /** Called ketika RecyclerView membutuh ViewHolder baru dari tipe untuk mewakili item
     *
     *
     * ViewHolder yang baru akan di constructed dengan View baru dan bisa mewakili item dari tipe yag diberikan.
     * memanggil worldlist_item.xml
     *
     * ViewHolder baru akan digunakan untuk menampilkan item adapter menggunakan
     * onBindViewHolder(ViewHolder, int, int, List). Akan digunakan lagi untuk menampilkan item berbeda
     * dalam kumpulan data.
     *
     * parent : ViewGroup menampilkan View baru yg akan ditambahkan setelah terikat ke posisi adaptor.
     * viewType : jenis View baru. @return untuk menyimpan tampilan dari tampilan yang diberikan.
     */

    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.wordlist_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    /**
     * Called by RecyclerView untuk menampilkan data yang telah ditentukan posisinya.
     * Method yg digunakan untuk update contents dari ViewHolder.itemView
     *
     * holder   ViewHolder yg di update di contents item yg diberikan dalam kumpulan data
     * position posisi item dalam set data Adapter
     */

    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder,
                                 int position) {
        // mengambil data untuk position
        String mCurrent = mWordList.get(position);
        // menambahkan data ke view holder
        holder.wordItemView.setText(mCurrent);
    }

    /**
     * jumlah total item dalam set data dari adapter.
     *
     * @return jumlah total item di adapter
     */

    @Override
    public int getItemCount() {
        // method to return the size of mWordList
        return mWordList.size();
    }
}