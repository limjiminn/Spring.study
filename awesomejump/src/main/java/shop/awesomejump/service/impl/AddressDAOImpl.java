package shop.awesomejump.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.awesomejump.domain.AddressDTO;
import shop.awesomejump.mapper.AddressMapper;
import shop.awesomejump.service.IAddressDAO;

@Service
@RequiredArgsConstructor
public class AddressDAOImpl implements IAddressDAO {
	
	private final AddressMapper addressMapper;
	
	@Override
	public boolean insertAddress(AddressDTO address) {
		return addressMapper.insertAddress(address) == 1;
	}

	@Override
	public boolean updateAddress(AddressDTO address) {
		return addressMapper.updateAddress(address) == 1;
	}
	
	@Override
	public boolean deleteAddress(int address_no) {
		return addressMapper.deleteAddress(address_no) == 1;
	}
	
	@Override
	public List<AddressDTO> selectAddressList(int mem_no) {
		return addressMapper.selectAddressList(mem_no);
	}

	@Override
	public AddressDTO selectAddress(int address_no) {
		return addressMapper.selectAddress(address_no);
	}
	
}