package com.example.photogallery1.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.photogallery1.Controls.ProgressDialogRtl;
import com.example.photogallery1.R;
import com.example.photogallery1.adapter.AnswersAdapter;
import com.example.photogallery1.model.CategoryResponse;
import com.example.photogallery1.model.Item;
import com.example.photogallery1.service.ApiClient;
import com.example.photogallery1.service.ApiInterface;
import com.example.photogallery1.service.ApiService;
import com.example.photogallery1.service.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.input;


/**
 * Created by LeilaArbab  on 12/4/2017.
 */
public class MainActivity extends AppCompatActivity {

    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ApiService mService;

   public String searchTerms;


   public Button searchPhotos;


 public EditText searchText;
    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mService = ApiUtils.getApiService();

//        btnSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String searchTerms = txtSearch.getText().toString();
//
//                // Empty search term
//                if (searchTerms.length() == 0) {
//                    Toast.makeText(GalleryActivity.this, R.string.gallery_empty_search_terms, Toast.LENGTH_SHORT)
//                            .show();
//                    return;
//                }
//
//                // API Call to search photos with user provided search term
//                mFlickrService.searchPhotos(searchTerms, PHOTOS_PER_PAGE, "1", new Callback<PhotoResponse>() {
//                    @Override
//                    public void success(PhotoResponse photoResponse, Response response) {
//                        Log.d(TAG, photoResponse.toString());
//                        displayPhotos(photoResponse.getPhotos().getList());
//                    }
//
//                    @Override
//                    public void failure(RetrofitError retrofitError) {
//                        if (retrofitError.isNetworkError()) {
//                            Toast.makeText(GalleryActivity.this, R.string.error_network, Toast.LENGTH_SHORT).show();
//                        }
//
//                        Log.e(TAG, "Error retrieving photos", retrofitError);
//                    }
//                });
//
//            }
//        });
//    }
//
//    private void displayPhotos(List<Photo> photos) {
//        mPhotoListAdapter.setPhotos(photos);
//    }
//
//}
//





        searchText = (EditText) findViewById(R.id.txtSearch);

   searchPhotos = (Button)findViewById(R.id.btnSearch);
        searchPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      searchTerms = searchText.getText().toString();
        // Empty search term
        if (searchTerms.length() == 0) {
        Toast.makeText(MainActivity.this, R.string.gallery_empty_search_terms, Toast.LENGTH_SHORT)
        .show();
        return;
        }
                loadAnswerSerarch();

            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_answers);
        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(String id) {

            }


            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

     loadAnswers();
    }

    public void loadAnswers() {
        final ProgressDialogRtl dialogRtl = new ProgressDialogRtl(this);
        dialogRtl.setTitle(this.getString(R.string.progress_bar_text));
        dialogRtl.setCancelable(false);
        dialogRtl.show();
        // RxJava Implementation

        /*mService.getAnswers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SOAnswersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SOAnswersResponse soAnswersResponse) {
                        mAnswersView.showAnswers(soAnswersResponse.getItems());
                    }
                });*/

        ApiInterface client = ApiClient.getClient().create(ApiInterface.class);

        Call<CategoryResponse> call = client.getCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse item = response.body();
                    mAdapter.updateAnswers(item.getItems());
                    Log.d("AnswersPresenter", "posts loaded from API");
                    dialogRtl.dismiss();
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");
                dialogRtl.dismiss();

            }
        });
    }


    public void showErrorMessage() {
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

  public  void   loadAnswerSerarch()
  { final ProgressDialogRtl dialogRtl = new ProgressDialogRtl(this);
      dialogRtl.setTitle(this.getString(R.string.progress_bar_text));
      dialogRtl.setCancelable(false);
      dialogRtl.show();
      // RxJava Implementation

        /*mService.getAnswers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SOAnswersResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SOAnswersResponse soAnswersResponse) {
                        mAnswersView.showAnswers(soAnswersResponse.getItems());
                    }
                });*/

      ApiInterface client = ApiClient.getClient().create(ApiInterface.class);

      Call<CategoryResponse> call = client.getCategories(searchTerms);

      call.enqueue(new Callback<CategoryResponse>() {
          @Override
          public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
              if (response.isSuccessful()) {
                  CategoryResponse item = response.body();
                  mAdapter.updateAnswers(item.getItems());
                  Log.d("AnswersPresenter", "posts loaded from API");
                  dialogRtl.dismiss();
              } else {
                  int statusCode = response.code();
                  // handle request errors depending on status code
              }
          }

          @Override
          public void onFailure(Call<CategoryResponse> call, Throwable t) {
              showErrorMessage();
              Log.d("AnswersPresenter", "error loading from API");
              dialogRtl.dismiss();

          }
      });
  }
}
