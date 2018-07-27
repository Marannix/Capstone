package com.marannix.android.capstone.response;

import com.marannix.android.capstone.data.model.Cast;
import java.util.List;

public class CreditsResponse {

  private int id;
  private List<Cast> castList;

  public int getId() {
    return id;
  }

  public List<Cast> getCastList() {
    return castList;
  }
}
