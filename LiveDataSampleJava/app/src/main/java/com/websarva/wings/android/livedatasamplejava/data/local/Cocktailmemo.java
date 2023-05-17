package com.websarva.wings.android.livedatasamplejava.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cocktailmemos")
public class Cocktailmemo {
	@PrimaryKey
	public long id;
	@NonNull
	public String name;
	public String note;
}
