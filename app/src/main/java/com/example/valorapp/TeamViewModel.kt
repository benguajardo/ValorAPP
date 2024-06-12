import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valorapp.Team
import com.example.valorapp.TeamRepository

class ItemViewModel: ViewModel() {
    private val itemRepository = TeamRepository()
    val items: LiveData<Team> = itemRepository.getTeam()
    private val _team = MutableLiveData<Team>()

    val team: LiveData<Team> get() = _team

    fun loadTeam() {
        itemRepository.getTeam().observeForever { team ->
            _team.value = team
        }
    }
    fun resetTeam() {
        _team.value = Team("", "", "", "", "")
    }

}