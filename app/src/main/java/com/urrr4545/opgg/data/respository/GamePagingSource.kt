package com.urrr4545.opgg.data.respository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.urrr4545.opgg.data.network.OpggApi
import com.urrr4545.opgg.data.network.dto.GameDtoMapper
import com.urrr4545.opgg.domain.model.Game
import com.urrr4545.opgg.domain.model.Match
import com.urrr4545.opgg.domain.model.MatchList
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GamePagingSource
@Inject
constructor(
    private val api: OpggApi,
    private val gameDtoMapper: GameDtoMapper
    ): PagingSource<Long, Game>(){

    override fun getRefreshKey(state: PagingState<Long,Game>): Long? {
        return null
    }

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, Game> {
        return try {
            val lastCreateDate = params.key?: (System.currentTimeMillis() / 1000)
            Log.e("vv","load paging data ${lastCreateDate}")
            val response = api.getMatches(lastCreateDate)
            val gameData = gameDtoMapper.toDomainList(response.games)
            var lastData = gameData.last().createDate

            LoadResult.Page(
                data = gameData,
                prevKey = null,
                nextKey = lastData
            )
        } catch (e : IOException) {
            LoadResult.Error(e)
        } catch (e : HttpException) {
            LoadResult.Error(e)
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}