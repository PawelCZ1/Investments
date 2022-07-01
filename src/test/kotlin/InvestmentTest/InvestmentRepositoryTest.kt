package InvestmentTest

import com.pawelcz.investments.investment.InvestmentRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class InvestmentRepositoryTest(private var investmentRepository: InvestmentRepository) {


    @Test
    fun allInvestments(){
        // given

    }
}